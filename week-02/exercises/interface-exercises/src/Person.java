public class Person {

    private MoneyStorage wallet;
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.wallet = new Wallet(0, firstName + "'s wallet");
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public boolean deposit(double amount) {
        return wallet.deposit(amount);
    }

    public String getDescription() {
        return wallet.getDescription();
    }

    public double getBalance() {
        return wallet.getBalance();
    }
}
