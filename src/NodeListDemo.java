import node.NodeDemo;
import java.util.*;


public class NodeListDemo {
    private NodeDemo nodeParent;
    private List<NodeDemo>nodeChildren;

    public NodeDemo getNodeParent() {
        return nodeParent;
    }

    public void setNodeParent(NodeDemo nodeParent) {
        this.nodeParent = nodeParent;
    }

    public List<NodeDemo> getNodeChildren() {
        return nodeChildren;
    }

    public void setNodeChildren(List<NodeDemo> nodeChildren) {
        this.nodeChildren = nodeChildren;
    }

    public void toStringNodeList(){
        System.out.println("NodeParent: "+"("+nodeParent.getX()+","+nodeParent.getY()+")->");
        for (NodeDemo node:nodeChildren) {
            System.out.printf("("+node.getX()+","+node.getY()+")");
        }
        System.out.printf("\n");
    }
}
