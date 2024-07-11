import java.util.Scanner;

interface Table {
    void createTable();
    void insertData(String data);
    void updateData(int id, String newData);
    void deleteData(int id);
    String fetchData(int id);
}

class DatabaseTable implements Table {
    String tableName;
    String[] data;

    public void initializeDatabaseTable(String name, int size) {
        tableName = name;
        data = new String[size];
    }

    public void createTable() {
        System.out.println("Table " + tableName + " created.");
    }

    public void insertData(String newData) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                data[i] = newData;
                System.out.println("Data inserted into " + tableName + ": " + newData);
                return;
            }
        }
        System.out.println("Table " + tableName + " is full.");
    }
    
    public void updateData(int id, String newData) {
        if (id >= 0 && id < data.length && data[id] != null) {
            data[id] = newData;
            System.out.println("Data at ID " + id + " in " + tableName + " updated to: " + newData);
        } else {
            System.out.println("Invalid ID or no data at ID " + id + " in " + tableName);
        }
    }

    public void deleteData(int id) {
        if (id >= 0 && id < data.length && data[id] != null) {
            data[id] = null;
            System.out.println("Data at ID " + id + " in " + tableName + " deleted.");
        } else {
            System.out.println("Invalid ID or no data at ID " + id + " in " + tableName);
        }
    }

    public String fetchData(int id) {
        if (id >= 0 && id < data.length && data[id] != null) {
            return data[id];
        }
        return "No data at ID " + id + " in " + tableName;
    }
}

class DatabaseOperation {
    Table tableInstance;

    public void initializeOperation(Table table) {
        tableInstance = table;
        table.createTable();
    }

    public void insert(String data) {
        tableInstance.insertData(data);
    }

    public void update(int id, String newData) {
        tableInstance.updateData(id, newData);
    }

    public void delete(int id) {
        tableInstance.deleteData(id);
    }

    public void fetch(int id) {
        String data = tableInstance.fetchData(id);
        System.out.println("Data at ID " + id + ": " + data);
    }
}

public class DatabaseManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create and initialize tables
        DatabaseTable userTable = new DatabaseTable();
        userTable.initializeDatabaseTable("UserTable", 5);

        DatabaseTable productTable = new DatabaseTable();
        productTable.initializeDatabaseTable("ProductTable", 5);

        // Perform operations on user table
        DatabaseOperation userOperation = new DatabaseOperation();
        userOperation.initializeOperation(userTable);

        System.out.println("User Table Operations:");
        System.out.print("Enter data to insert into UserTable: ");
        String userData = scanner.nextLine();
        userOperation.insert(userData);

        System.out.print("Enter ID to fetch data from UserTable: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        userOperation.fetch(userId);

        System.out.print("Enter ID to update data in UserTable: ");
        int updateUserIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        System.out.print("Enter new data: ");
        String newUserData = scanner.nextLine();
        userOperation.update(updateUserIndex, newUserData);

        System.out.print("Enter ID to delete data from UserTable: ");
        int deleteUserIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        userOperation.delete(deleteUserIndex);

        // Perform operations on product table
        DatabaseOperation productOperation = new DatabaseOperation();
        productOperation.initializeOperation(productTable);

        System.out.println("\nProduct Table Operations:");
        System.out.print("Enter data to insert into ProductTable: ");
        String productData = scanner.nextLine();
        productOperation.insert(productData);

        System.out.print("Enter ID to fetch data from ProductTable: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); 
        productOperation.fetch(productId);

        System.out.print("Enter ID to update data in ProductTable: ");
        int updateProductIndex = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new data: ");
        String newProductData = scanner.nextLine();
        productOperation.update(updateProductIndex, newProductData);

        System.out.print("Enter ID to delete data from ProductTable: ");
        int deleteProductIndex = scanner.nextInt();
        scanner.nextLine();
        productOperation.delete(deleteProductIndex);

    }
}
