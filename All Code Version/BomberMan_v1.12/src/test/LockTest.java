package test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test{
    Lock lock = new ReentrantLock();
    int num = 0;
    public void numIncrease() {
        lock.lock();
        num++;
        lock.unlock();
    }

    public void numDecrease() {
        num--;
    }
}

class ThreadTest1 {
    Timer t = new Timer();
    Test test;

    public ThreadTest1(Test test) {
        this.test = test;
        threadIncrease();
    }
    private void threadIncrease() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                test.numIncrease();
                showNum();
            }
        }, 0, 1000);
    }
    void showNum() {
        System.out.println(Thread.currentThread().getName() + "...." + test.num);
    }
}
class ThreadTest2 {
    Timer t = new Timer();
    Test test;

    public ThreadTest2(Test test) {
        this.test = test;
        threadDecrease();
    }
    private void threadDecrease() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                test.numDecrease();
                showNum();
            }
        }, 0, 100);
    }
    void showNum() {
        System.out.println(Thread.currentThread().getName() + "...." + test.num);
    }
}


public class LockTest {

    public static void main(String[] args) {
        Test test = new Test();
        new ThreadTest1(test);
        new ThreadTest2(test);
    }
}
