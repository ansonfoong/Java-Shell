package ext.anson;

public class LinkedList {
	
	Node root = new Node("C:");
	
	public LinkedList()
	{
		
	}
	
	public boolean isEmpty()
	{
		return root.next == null;
	}
	
	public void printList()
	{
		Node temp = root;
		while(temp != null)
		{
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	public String getPath()
	{
		StringBuilder pathName = new StringBuilder();
		Node temp = root;
		while(temp != null)
		{
			pathName.append(temp.data + "\\");
			temp = temp.next;
		}
		return pathName.toString();
	}
	public String pathWithoutSlash()
	{
		StringBuilder pathName = new StringBuilder();
		Node temp = root;
		while(temp != null)
		{
			pathName.append(temp.data);
			temp = temp.next;
		}
		return pathName.toString();
	}
	public void addNode(String data)
	{
		Node temp = root;
		if(isEmpty())
			temp.next = new Node(data);
		else
		{
			temp = root;
			while(temp.next != null)
			{
				temp = temp.next;
			}
			temp.next = new Node(data);
			temp.next.prev = temp;
		}
	}
	/*
	 * Deletes every single node up until the current node passed in.
	 * if flag = true, delete every node INCLUDING the node passed in.
	 * else, delete every node up to, but NOT the node passed in.
	 */
	public void currentDir(String currentDirName, boolean flag)
	{
		// Remove EVERY node from the end until you reach the dirName
		if(flag) // The root will never be deleted.
		{
			Node temp = root;
			while(temp.next != null)
			{
				if(temp.next.data.equals(currentDirName))
				{
					temp.next.prev = null;
					temp.next = null;
					return;
				}
				else
					temp = temp.next;
			}
		}
		else
		{
			Node temp = root;
			while(temp != null)
			{
				if(temp.data.equals(currentDirName))
				{
					temp.next = null;
					return;
				}
				else
				{
					temp = temp.next;
				}
			}
		}
	}
	public void popNode()
	{
		// Remove the last node. This happens every time we do "cd .. to go back one directory"
		Node temp = root;
		while(temp.next != null)
		{
			temp = temp.next;
		}
		// If temp.next is null and no iteration occurs, then we are at the main C: root, which we should not delete.
		// We're at the end. Remove the node.
		if(temp.data.equals(root.data))
			return;
		temp.prev.next = null;
		temp.prev = null;
		temp = null;
	}
	
	public void destroyList()
	{
		root.next = null;
	}
	
}

class Node 
{
	String data;
	Node next;
	Node prev;
	
	public Node()
	{
		
	}
	public Node(String data)
	{
		this.data = new String(data);
	}
	
}