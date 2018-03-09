package br.pedroso.tweetsentiment.domain.network.dataSources

import br.pedroso.tweetsentiment.domain.Tweet
import br.pedroso.tweetsentiment.domain.User
import io.reactivex.Observable

/**
 * Created by felip on 08/03/2018.
 */
interface TwitterDataSource {
    fun getUser(userName: String): Observable<User>
    fun getTweetsSinceTweet(user: User, tweet: Tweet): Observable<Tweet>
    fun getLatestTweetsFromUser(user: User): Observable<Tweet>
}