package com.klay.send

class PostMain {
    static main(args) {

        String path = 'C:\\Users\\sales\\Downloads\\docs\\20181210_1133\\data'
        int threads = 10
        def folder = new File(path)

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
