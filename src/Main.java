// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.insertEl(10);
        bt.insertEl(20);
        System.out.println(bt.root.value);
        System.out.println(bt.root.color);

    }
}

enum NodeType {
    RED,
    BLACK
}

class NodeBinaryTree {
    int value;
    NodeBinaryTree left;
    NodeBinaryTree right;
    NodeType color;

    public NodeBinaryTree(int value) {
        this.value = value;
    }
}

class BinaryTree {
    NodeBinaryTree root;

    public boolean isEmpty() {
        return root == null;
    }

    public void insertEl(int value) {
        if (root != null) {
            addElement(value, root);
        } else {
            NodeBinaryTree node = new NodeBinaryTree(value);
            node.color = NodeType.BLACK;
            root = node;
        }
    }

    private void addElement(int value, NodeBinaryTree currentNode) {
        if (currentNode.value == value) {
            System.out.println("такой элемент уже есть");
        } else {
            if (value < currentNode.value) {
                if (currentNode.left != null) {
                    addElement(value, currentNode.left);
                } else {
                    NodeBinaryTree node = new NodeBinaryTree(value);
                    currentNode.left = node;
                    fixColor(node);
                }
            } else if (value > currentNode.value) {
                if (currentNode.right != null) {
                    addElement(value, currentNode.right);
                } else {
                    NodeBinaryTree node = new NodeBinaryTree(value);
                    currentNode.right = node;
                    fixColor(node);
                }
            }
        }
    }

    public boolean searchElement(int value) {
        if (root != null) {
            return searchElementBack(value, root);
        } else {
            System.out.println("Дерево пустое");
            return false;
        }
    }

    private boolean searchElementBack(int value, NodeBinaryTree currentNode) {
        if (currentNode.value == value) {
            return true;
        } else if (value < currentNode.value) {
            if (currentNode.left != null) {
                return searchElementBack(value, currentNode.left);
            }
        } else if (value > currentNode.value) {
            if (currentNode.right != null) {
                return searchElementBack(value, currentNode.right);
            }
        }
        return false;
    }

    private void swapColor(NodeBinaryTree node) {
        if (node.color == NodeType.RED) {
            node.color = NodeType.BLACK;
        } else {
            node.color = NodeType.RED;
        }
    }

    private void fixColor(NodeBinaryTree currentNode) {
        if (currentNode.value == root.value) {
            currentNode.color = NodeType.BLACK;
        } else if (currentNode.color == NodeType.RED && currentNode.left.color == NodeType.RED) {
            if (currentNode.right.color == NodeType.RED) {
                swapColor(currentNode);
            } else {
                if (currentNode.left.color == NodeType.RED) {
                    rotateRight(currentNode);
                    swapColor(currentNode);
                }
            }
        }
    }

    private void rotateRight(NodeBinaryTree currentNode) {
        if (currentNode.left == null) {
            return;
        }

        NodeBinaryTree leftChild = currentNode.left;
        currentNode.left = leftChild.right;
        leftChild.right = currentNode;
        if (currentNode.value == root.value) {
            root = leftChild;
        }
    }
}

