package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Anv√§nds ocks√• i BSTVisaulizer
	int size; // Anv√§nds ocks√• i BSTVisaulizer
	private Comparator<E> comparator;

	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		BSTVisualizer vis = new BSTVisualizer("Tree viewer", 600, 600);
		
		bst.add(30);
		bst.add(20);
		bst.add(10);
		bst.add(40);
		bst.add(50);
		bst.add(5);
		bst.printTree();
		bst.rebuild();
		vis.drawTree(bst);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (this.root == null) {
			this.root = new BinaryNode<>(x);
			this.size++;
			return true;
		}
		return add(this.root, x);
	}

	private boolean add(BinaryNode<E> n, E x) {
		BinaryNode<E> node = new BinaryNode<>(x);

		if (comparator.compare(x, n.element) == 0) {
			return false;
		}

		// Hˆger
		else if (comparator.compare(x, n.element) > 0) {
			if (n.right == null) {
				n.right = node;
				this.size++;
			} else {
				return add(n.right, x);
			}
		}
		// V‰nster
		else {
			if (n.left == null) {
				n.left = node;
				this.size++;
			} else {
				return add(n.left, x);
			}
		}

		return true;
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return heightRec(root);
	}

	private int heightRec(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		}
		return 1 + Math.max(heightRec(n.left), heightRec(n.right));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> n) {
		if( n != null) {
			printTree(n.left);
			System.out.println(" " + n.element);
			printTree(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<>();
		toArray(root, sorted);
		root = buildTree(sorted, 0, sorted.size() - 1 );
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first > last) {
			return null;
		}
		int mid = (first + last) / 2;
		BinaryNode<E> node = new BinaryNode<>(sorted.get(mid));
		
		// V‰nster
		node.left = buildTree(sorted, first, mid -1);
		
		// Hˆger
		node.right = buildTree(sorted, mid + 1, last);
		
		return node;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
