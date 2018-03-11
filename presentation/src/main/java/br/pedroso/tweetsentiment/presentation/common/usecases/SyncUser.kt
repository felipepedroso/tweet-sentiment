package br.pedroso.tweetsentiment.presentation.common.usecases

import br.pedroso.tweetsentiment.domain.User
import br.pedroso.tweetsentiment.presentation.shared.usecases.GetUserFromApi
import br.pedroso.tweetsentiment.presentation.shared.usecases.GetUserFromDatabase
import br.pedroso.tweetsentiment.presentation.shared.usecases.RegisterUserOnDatabase
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Created by felip on 09/03/2018.
 */
class SyncUser(
        private val scheduler: Scheduler,
        private val getUserFromApi: GetUserFromApi,
        private val registerUserOnDatabase: RegisterUserOnDatabase,
        private val getUserFromDatabase: GetUserFromDatabase) {

    fun execute(username: String): Observable<User> {
        return getUserFromApi.execute(username)
                .subscribeOn(scheduler)
                .doOnNext { updateUserOnDatabase(it) }
    }

    private fun updateUserOnDatabase(user: User) {
        getUserFromDatabase.execute(user.userName)
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .defaultIfEmpty(user)
                .subscribe {
                    if (it != user) {
                        registerUserOnDatabase.execute(it)
                    }
                }
    }
}