package synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank
{
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException
    {
        System.out.print("Blokada " + Thread.currentThread());
        bankLock.lock();
        try
        {
            while (accounts[from] < amount)
                sufficientFunds.await();
            accounts[from] -= amount;
            System.out.printf(" %10.2f z %dd na %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Saldo ogólne: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        }
        finally
        {
            bankLock.unlock();
        }
    }

    public double getTotalBalance()
    {
        bankLock.lock();
        try
        {
            double sum = 0;

            for (double a : accounts)
                sum += a;

            return sum;
        }
        finally
        {
            bankLock.unlock();
        }
    }

    public int size()
    {
        return accounts.length;
    }

}
