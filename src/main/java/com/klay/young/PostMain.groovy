/*
package com.klay.young

import com.youngfey.restful.Api
import com.youngfey.restful.Request
import org.springframework.http.HttpMethod

class PostMain {
    static main(args) {

        String path = 'C:\\Users\\sales\\Downloads\\docs\\20181210_1142\\data'
        int threads = 10
        def folder = new File(path)

        Api api=new Api()
        api.url="http://rest.youngfey.com:8080/netsuite/api?script=158&deploy=5&user=dai"
        def findFile = { idx ->
            folder.listFiles().find {
                it?.name?.endsWith('.json') && (it.name - '.json')?.split('-')[-1].toInteger() % threads == idx
            }
        }
        threads.times { threadId ->
            Thread.start {
                while(true) {
                    def file = findFile threadId
                    if(!file) break
                    println "Threads: $threadId - $file.name"

                    api.send(new Request(HttpMethod.POST, ["Content-Type":"application/json", "Authorization":"NLAuth nlauth_account=5179288_SB1,nlauth_email=newton.chen@hitpointcloud.com,nlauth_signature=Netsuite123, nlauth_role=1000"],
                            ["script":"158","deploy":"5"], file.getText("utf8")))
                    file.renameTo(new File(file.absolutePath - '.json' + '.done'))
                }
                println "Done: Thread $threadId"
            }
        }

        while(true) {
            def file = folder.listFiles().find { it?.name?.endsWith('.json') }
            if(file) sleep 1000
            else break
        }
    }
}
*/
