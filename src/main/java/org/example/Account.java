package org.example;

public class Account {

        private /*volatile*/ long balance;

        public Account() {
            this(0L);
        }

        public Account(long balance) {
            this.balance = balance;
        }

        public long getBalance() {
            return balance;
        }

        public synchronized void deposit(long amount) {
            checkAmountNonNegative(amount);
            balance += amount;
        }

        public synchronized void withdraw(long amount) {
            checkAmountNonNegative(amount);
            if (balance < amount) {
                throw new IllegalArgumentException("not enough money");
            }
            balance -= amount;
        }

        private static void checkAmountNonNegative(long amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("negative amount");
            }
        }
}
