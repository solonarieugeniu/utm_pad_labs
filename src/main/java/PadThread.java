import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import static java.util.Objects.isNull;

@Getter
@Setter
@RequiredArgsConstructor
@Log4j
public class PadThread implements Runnable {

    private Thread[] threads;

    public void run() {
        waitThreads(threads);
        log.info("EXECUTED");
    }

    private void waitThreads(Thread[] threads) {
        if (isNull(threads)) {
            return;
        }
        log.info("Thread state: " + Thread.currentThread().getState() + ", Wait - In Progress");

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                thread.interrupt();
                log.error("Thread interrupted");
            }
        }

        log.info(Thread.currentThread().getName() + ", Wait - Done");
    }
}
