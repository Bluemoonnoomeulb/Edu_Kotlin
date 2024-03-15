package main.api

import java.net.URI as NetURI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object ExternalAPI {
    fun executeRequest(uri: URI, countParam: Int = 0): String {
        val requestUri = createUri(uri, countParam)

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(NetURI.create(requestUri))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        return response.body()
    }

    private fun createUri(uri: URI, countParam: Int): String {
        return if (uri == URI.MAIN)
            uri.url + uri.params + "&count=" + countParam
        else
            uri.url + uri.params
    }
}
