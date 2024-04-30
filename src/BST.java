import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private Node (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        Node newNode = new Node(key, value);
        root = put(root, newNode);
    }

    public V get(K key) {
        Node rootTemp = root;
        return get(root, key);
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node put(Node root, Node newNode) {

        if (root == null) {
            size++;
            return new Node(newNode.key, newNode.value);
        }

        int compareNum = newNode.key.compareTo(root.key);

        if (compareNum < 0) {
            root.left = put(root.left, newNode);
        } else if (compareNum > 0) {
            root.right = put(root.right, newNode);
        } else {
            root.value = newNode.value;
        }

        return root;
    }

    private V get(Node root, K key) {

        if (root == null) {
            return null;
        }

        int compareNum = key.compareTo(root.key);

        if (compareNum < 0) {
            return get(root.left, key);
        } else if (compareNum > 0) {
            return get(root.right, key);
        } else {
            return root.value;
        }
    }

    private Node delete(Node root, K key) {

        if (root == null) {
            return null;
        }

        int compareNum = key.compareTo(root.key);

        if (compareNum < 0) {
            root.left = delete(root.left, key);
        } else if (compareNum > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;

            Node temp = min(root.right);
            root.key = temp.key;
            root.value = temp.value;
            root.right = deleteMin(root.right);
            size--;
        }

        return root;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    public int length() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<K> {
        private Stack<Node> stack;

        public InOrderIterator(Node root) {
            stack = new Stack<>();
            pushAll(root);
        }

        public void pushAll(Node root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node node = stack.pop();
            pushAll(node.right);
            return node.key;
        }
        
    }


}
