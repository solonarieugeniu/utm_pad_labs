import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

@Log4j
class PadThreadsMainTest {

    private static final String THREAD = "Thread: ";

    public static void main(String[] args) {

        Map<String, Thread> mapOfThreadsToWait = new HashMap<>();
        Map<String, PadThread> mapOfGenericThread = new HashMap<>();

        for (int i = 1; i <= 6; i++) {
            String threadName = THREAD + i;

            PadThread myGenericThread = new PadThread();

            mapOfGenericThread.put(threadName, myGenericThread);
            mapOfThreadsToWait.put(threadName, new Thread(myGenericThread, threadName));
        }

        mapOfGenericThread.get(THREAD + 6)
            .setThreads(new Thread[]{mapOfThreadsToWait.get(THREAD + 5), mapOfThreadsToWait.get(THREAD + 4)}
            );

        mapOfGenericThread.get(THREAD + 5)
            .setThreads(new Thread[]{mapOfThreadsToWait.get(THREAD + 4)});

        mapOfGenericThread.get(THREAD + 4)
            .setThreads(new Thread[]{mapOfThreadsToWait.get(THREAD + 2), mapOfThreadsToWait.get(THREAD + 3)});

        mapOfGenericThread.get(THREAD + 3)
            .setThreads(new Thread[]{mapOfThreadsToWait.get(THREAD + 1), mapOfThreadsToWait.get(THREAD + 2)});

        mapOfGenericThread.get(THREAD + 2)
            .setThreads(new Thread[]{mapOfThreadsToWait.get(THREAD + 1)});

        log.info("Started threads");
        mapOfThreadsToWait.values().forEach(Thread::start);
    }
}
