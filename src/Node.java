import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
	int [][] data;
	boolean visited;
	List<Node> children;
    private Node parent;
    private int cost;
    private int depth;
    //constructor
	Node(int [][] data)
		{
		this.data=data;
		this.children=new ArrayList<>();
		}
	@Override
	public String toString() {
		return "Node [cost=" + cost + "]";
	}
	public int getDepth() {
    return depth;
    }
	public void setDepth(int depth) {
    this.depth = depth;
	}	
	public void addChildren(Node childrenNode)
		{
		this.children.add(childrenNode);
		}
	public List<Node> getChildren() {
		return children;
		}
	public void setChildren(List<Node> children) {
		this.children = children;
		}
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
	@Override
	public int compareTo(Node arg0) {
		return (this.getCost()-arg0.getCost());
	}
}
