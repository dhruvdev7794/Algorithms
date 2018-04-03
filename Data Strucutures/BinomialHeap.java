import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

class BinomialHeapNode{
	private int key, degree;
	private BinomialHeapNode parent;
	private BinomialHeapNode sibling;
	private BinomialHeapNode child;
	
	public BinomialHeapNode(int k) {
		key = k;
		degree = 0;
		parent = null;
		sibling = null;
		child = null;
	}
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public BinomialHeapNode getParent() {
		return parent;
	}

	public void setParent(BinomialHeapNode parent) {
		this.parent = parent;
	}

	public BinomialHeapNode getSibling() {
		return sibling;
	}

	public void setSibling(BinomialHeapNode sibling) {
		this.sibling = sibling;
	}

	public BinomialHeapNode getChild() {
		return child;
	}

	public void setChild(BinomialHeapNode child) {
		this.child = child;
	}
}
public class BinomialHeap {
	
	BinomialHeapNode head;
	
	// make binomial heap function //
	BinomialHeap(){
		head = null;
	}
	BinomialHeap(BinomialHeapNode head){
		this.head = head;
	}
	/////////////////////////////////
	
	// Returns the HeapMinimum
	// It looks at all the siblings of the head and finds the minimum
	// OUTPUT - BinomialHeapNode variable with the minimum key
	public BinomialHeapNode binomialHeapMinimum() {
		if(head == null)
			return null;
		else {
			BinomialHeapNode min = head;
			BinomialHeapNode next = min.getSibling();
			
			while(next != null) {
				if(next.getKey() < min.getKey())
					min = next;
				next = next.getSibling();
			}
			return min;
		}
	}
	////////////////////////////////////////////////////////////////////
	// input:
	//	1. head of type BinomialHeapNode which is going to be child node
	//	2. head of type BinomialHeapNode which is going to be parent node
	// output: nothing. it just links one to another.
	public void binomialLink(BinomialHeapNode oldRoot, BinomialHeapNode newRoot) {
		// attach something to something
		oldRoot.setParent(newRoot);
		oldRoot.setSibling(newRoot.getChild());
		newRoot.setChild(oldRoot);
		newRoot.setDegree(newRoot.getDegree()+1);
	}
	
	// input - merges two binomial heaps into one
	// output - head of the merged BinomialHeap
	public BinomialHeapNode binomialHeapMerge(BinomialHeap h1, BinomialHeap h2) {
		if(h1.head == null)
			return h2.head;
		else if(h2.head == null)
			return h1.head;
		else {
			BinomialHeapNode head;
			BinomialHeapNode h1Next = h1.head;
			BinomialHeapNode h2Next = h2.head;
//			System.out.println(h1.head.getKey());
//			System.out.println(h2.head.getKey());
			if(h1.head.getDegree() <= h2.head.getDegree()) {
				head = h1.head;
				h1Next = h1Next.getSibling();
//				System.out.println(h1Next);
			}
			else {
				head = h2.head;
				h2Next = h2Next.getSibling();
			}
			
			BinomialHeapNode tail = head;
			
			while(h1Next!=null && h2Next!=null) {
				if(h1Next.getDegree() <= h2Next.getDegree()) {
					tail.setSibling(h1Next);
					h1Next = h1Next.getSibling();
				}
				else {
					tail.setSibling(h2Next);
					h2Next = h2Next.getSibling();
				}
				tail = tail.getSibling();
			}
			if(h1Next!=null)
				tail.setSibling(h1Next);
			else
				tail.setSibling(h2Next);
			
			return head;
		}
	}
	
//	public void display() {
////		if(head!=null)
////			head.display1(0);
//		while(head != null) {
//			BinomialHeapNode current = head;
//			int nodesWithChildren = current.getDegree() - 1;
//			System.out.println(current.getKey());
//			
//			Queue<BinomialHeapNode> children = new LinkedList<>();
//			
//			current = current.getChild();
//			
//			while(current!=null) {
//				System.out.print(current.getKey() + "  ");
//				if(current.getChild()!=null) {
//					BinomialHeapNode sibling = current.getSibling();
//					while(sibling!=null) {
//						System.out.print(current.getSibling().getKey() + "  ");
//						sibling = sibling.getSibling();
//					}
////					current = current.getChild();
//				}
//				System.out.println();
//				current = current.getChild();
//			}
//			System.out.println();
//			head = head.getSibling();
//		}
//	}
	
	public void display() {
		BinomialHeapNode root = head;
		while(root != null) {
			System.out.println(root.getKey());
			Queue<BinomialHeapNode> children = new LinkedList<>();
			children.add(root.getChild());
			while(!children.isEmpty()) {
				
				Iterator<BinomialHeapNode> itr = children.iterator();
				BinomialHeapNode current = itr.next();
				itr.remove();
				
				if(current!=null) {
					System.out.print(current.getKey() + "  ");
					
					if(current!=null && current.getChild()!=null) {
						children.add(current.getChild());
					}
					BinomialHeapNode sibling = current.getSibling();
					while(sibling!=null) {
						System.out.print(sibling.getKey() + "  ");
						if(sibling!=null && sibling.getChild()!=null) {
							children.add(sibling.getChild());
						}
						sibling = sibling.getSibling();
					}
//					BinomialHeapNode newcurrent = itr.next();
					
				}
				
				System.out.println();
			}
			System.out.println();
			root = root.getSibling();
		}
	}
	
	
	public BinomialHeapNode search(int key) {
		BinomialHeapNode root = head;
		while(root != null) {
			if(root.getKey() == key)
				return root;
			Queue<BinomialHeapNode> children = new LinkedList<>();
			children.add(root.getChild());
			while(!children.isEmpty()) {
				Iterator<BinomialHeapNode> itr = children.iterator();
				BinomialHeapNode current = itr.next();
				itr.remove();
				
				if(current!=null) {
					if(current.getKey() == key) {
						return current;
					}
					
					if(current!=null && current.getChild()!=null) {
						children.add(current.getChild());
					}
					BinomialHeapNode sibling = current.getSibling();
					while(sibling!=null) {
						if(sibling.getKey() == key) {
							return sibling;
						}
						if(sibling!=null && sibling.getChild()!=null) {
							children.add(sibling.getChild());
						}
						sibling = sibling.getSibling();
					}
//					BinomialHeapNode newcurrent = itr.next();
				}
			}
			root = root.getSibling();
		}
		return null;
	}
	
	
	
	
	// input - BinomialHeap that needs to be UNIONED with current instance's BinomialHeap
	// output - BinomialHeadNode with head
	public BinomialHeapNode union(BinomialHeap heap) {
		BinomialHeapNode newHead = binomialHeapMerge(this, heap);
		head = null;
		heap.head = null;
		if(newHead == null)
			return null;
		BinomialHeapNode prev_x = null;
		BinomialHeapNode x = newHead;
		BinomialHeapNode next_x = newHead.getSibling();
		
		while(next_x != null) {
			if(x.getDegree() != next_x.getDegree() || (next_x.getSibling() != null && next_x.getSibling().getDegree() == x.getDegree())) {
				prev_x = x;
				x = next_x;
			}
			else{
				if(x.getKey()<=next_x.getKey()) {
					x.setSibling(next_x.getSibling());
					binomialLink(next_x, x);
				}
				else {
					if(prev_x == null) {
						newHead = next_x;
					}
					else {
						prev_x.setSibling(next_x);
					}
					binomialLink(x, next_x);
					x = next_x;
				}
			}
			next_x = x.getSibling();
		}
		return newHead;
	}
	
	// input - key is z
	// output - nothing
	public void insert(int x) {
		BinomialHeapNode node = new BinomialHeapNode(x);
		BinomialHeap heap = new BinomialHeap(node);
		head = union(heap);
	}
	
	// input - BinomialNode whose key is to be decreased and decreased key value
	public void binomialHeapDecreaseKey(BinomialHeapNode node, int key) {
		if(node.getKey() > key) {
			node.setKey(key);
			BinomialHeapNode y = node;
			BinomialHeapNode z = node.getParent();
//			System.out.println(z.getKey());
			while(z!=null && y.getKey()<z.getKey()) {
				int temp = y.getKey();
				y.setKey(z.getKey());
				z.setKey(temp);
				y = z;
				z = z.getParent();
//				System.out.println(z.getKey());
			}
		}
	}
	
	// input - BinomialHeapNode node which we need to delete
	public void binomialHeapDelete(BinomialHeapNode node) {
		binomialHeapDecreaseKey(node, binomialHeapMinimum().getKey() - 1);
		
		binomiaHeapExtractMin();
	}
	
	// we simply remove the minimum node
	public BinomialHeapNode binomiaHeapExtractMin() {
		if(head == null)
			return null;
		// Step 1a: find the root with minimum key
		BinomialHeapNode min = head;
		BinomialHeapNode prev = null;
		BinomialHeapNode next = min.getSibling();
		BinomialHeapNode nextPrev = min;
		
		while(next!=null) {
			if(next.getKey() < min.getKey()) {
				min = next;
				prev = next.getSibling();
			}
			nextPrev = next;
			next = next.getSibling();
		}
		System.out.println(min.getKey());
		// Step 1b and 2: remove min from root list and reverse the order of root's children.
		if(min == head) {
			head = min.getSibling();
		}
			
		else
			prev.setSibling(min.getSibling());
		
		BinomialHeapNode newHead = null;
		BinomialHeapNode child = min.getChild();
		while(child!=null) {
			BinomialHeapNode childNext = child.getSibling();
			child.setSibling(newHead);
			child.setParent(null);
			newHead = child;
			child = childNext;
		}
		
		// Step 3: make a new heap
		BinomialHeap newHeap = new BinomialHeap(newHead);
//		newHeap.display();
		// Step 4: union
		head = union(newHeap);
		// Step 5: return min node
		return min;
		
	}
	
	public static void main(String[] args) {
		BinomialHeap heap = new BinomialHeap(); 
		heap.insert(1);
		heap.insert(10);
		heap.insert(20);
		heap.insert(30);
		heap.insert(40);
		heap.insert(95);
		heap.insert(11);
		heap.insert(101);
		
		heap.insert(21);
		heap.insert(71);
		heap.insert(12);
		heap.insert(54);
		heap.insert(43);
		heap.insert(36);
		heap.insert(92);
		heap.insert(28);
		heap.display();
		System.out.println("---------------------------------");
		heap.binomiaHeapExtractMin();
		System.out.println("---------------------------------");
		heap.display();
	}
	
}
