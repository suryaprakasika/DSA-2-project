class Node {
    int orderId, price, volume;
    Node left, right;

    Node(int orderId, int price, int volume) {
        this.orderId = orderId;
        this.price = price;
        this.volume = volume;
        left = right = null;
    }
}

class OrderBookBST {
    Node root;
    Node bestBid; // O(1) peek best bid

    // Insert Order
    Node insert(Node root, int orderId, int price, int volume) {
        if (root == null) {
            Node newNode = new Node(orderId, price, volume);

            if (bestBid == null || price > bestBid.price) {
                bestBid = newNode;
            }

            return newNode;
        }

        // Descending BST (higher price left)
        if (price > root.price) {
            root.left = insert(root.left, orderId, price, volume);
        } else {
            root.right = insert(root.right, orderId, price, volume);
        }

        return root;
    }

    void insertOrder(int orderId, int price, int volume) {

        // Match instantly if >= 2988
        if (price >= 2988) {
            System.out.println("Order " + orderId +
                    " matched instantly and removed");
            return;
        }

        root = insert(root, orderId, price, volume);
    }

    // Peek Best Bid O(1)
    void peekBestBid() {
        if (bestBid != null) {
            System.out.println("Best Bid Price: " + bestBid.price);
        } else {
            System.out.println("No Orders");
        }
    }

    // Delete by Order ID
    Node delete(Node root, int orderId) {
        if (root == null)
            return null;

        if (root.orderId == orderId) {

            // No child
            if (root.left == null && root.right == null)
                return null;

            // One child
            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            // Two children
            Node temp = root.left;
            while (temp.right != null)
                temp = temp.right;

            root.orderId = temp.orderId;
            root.price = temp.price;
            root.volume = temp.volume;

            root.left = delete(root.left, temp.orderId);

            return root;
        }

        root.left = delete(root.left, orderId);
        root.right = delete(root.right, orderId);

        return root;
    }

    void deleteByOrderId(int orderId) {
        root = delete(root, orderId);
        System.out.println("Order " + orderId + " cancelled");
    }

    // Display BST
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(
                    "OrderID: " + root.orderId +
                    " Price: " + root.price +
                    " Volume: " + root.volume);
            inorder(root.right);
        }
    }

    void display() {
        inorder(root);
    }
}

public class Main {
    public static void main(String[] args) {

        OrderBookBST book = new OrderBookBST();

        // Orders from image
        book.insertOrder(101, 2980, 100);
        book.insertOrder(102, 2965, 100);
        book.insertOrder(103, 2992, 100);
        book.insertOrder(104, 2985, 100);
        book.insertOrder(105, 2970, 100);
        book.insertOrder(106, 2998, 100);
        book.insertOrder(107, 2978, 100);
        book.insertOrder(108, 2988, 100);

        System.out.println("\nRemaining Orders in BST:");
        book.display();

        System.out.println();
        book.peekBestBid();

        System.out.println("\nCancelling Order 104...");
        book.deleteByOrderId(104);

        System.out.println("\nUpdated BST:");
        book.display();
    }
}