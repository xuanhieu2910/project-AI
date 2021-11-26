import node.NodeDemo;
import org.w3c.dom.NodeList;

import javax.xml.soap.Node;
import java.util.*;
public class Algorithm {

    private Node node;
    private NodeList nodeList;
    private int[][]mazes = new int[30][30];
    private boolean[][]visited = new boolean[30][30];


    public void setNewVisited(){
        for (int i=0;i<30;i++){
            for(int j=0;j<30;j++){
                visited[i][j] = false;
            }
        }
    }

    public boolean[][] getVisited() {
        return visited;
    }

    /**
     *
     * Algorithm DFS
     *
     *
     * */
    public Stack<NodeDemo> dfs(int[][]mazes,boolean[][]visited,int xStart, int yStart,List<NodeDemo>positionNode){ // xStart : I , yStart : J ==> DFS

            Stack<NodeDemo> st = new Stack<>();
            NodeDemo nodeStart = new NodeDemo();
            nodeStart.setX(xStart);
            nodeStart.setY(yStart);
            st.push(nodeStart);
            visited[xStart][yStart] = true;
            while (!st.empty()) {
                NodeDemo node = st.pop();
                if (mazes[node.getX()][node.getY()] == 3) {
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX());
                    newNode.setY(node.getY());
                    visited[node.getX()][node.getY()] = true;
                    st.push(node);
                    st.push(newNode);
                    st.pop();
                    return st;
                } else if ((node.getY() < (mazes.length - 1)) && visited[node.getX()][node.getY() + 1] == false && mazes[node.getX()][node.getY() + 1] != 0 && checkPositionNode(positionNode, node.getX(), node.getY()+1)) { //Phai
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX());
                    newNode.setY(node.getY() + 1);
                    visited[node.getX()][node.getY() + 1] = true;
                    st.push(node);
                    st.push(newNode);
                } else if (node.getY() > 0 && visited[node.getX()][node.getY() - 1] == false && mazes[node.getX()][node.getY() - 1] != 0 && checkPositionNode(positionNode, node.getX(), node.getY()-1)) {//Trai
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX());
                    newNode.setY(node.getY() - 1);
                    visited[node.getX()][node.getY() - 1] = true;
                    st.push(node);
                    st.push(newNode);
                } else if (node.getX() < mazes.length - 1 && visited[node.getX() + 1][node.getY()] == false && mazes[node.getX() + 1][node.getY()] != 0 && checkPositionNode(positionNode, node.getX()+1, node.getY())) {//Xuong
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX() + 1);
                    newNode.setY(node.getY());
                    visited[node.getX() + 1][node.getY()] = true;
                    st.push(node);
                    st.push(newNode);
                } else if (node.getX() > 0 && visited[node.getX() - 1][node.getY()] == false && mazes[node.getX() - 1][node.getY()] != 0 && checkPositionNode(positionNode, node.getX()-1 , node.getY())) {//Len
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX() - 1);
                    newNode.setY(node.getY());
                    visited[node.getX() - 1][node.getY()] = true;
                    st.push(node);
                    st.push(newNode);
                }
            }
        return  null;
    }


    /**
     *
     * Algorithm BFS
     *
     *
     * */
    public LinkedList<NodeListDemo> bfs(int[][]mazes,boolean[][]visited,int xStart, int yStart,List<NodeDemo>positionNode){
        Queue<NodeDemo> qe = new ArrayDeque<>();
        LinkedList<NodeListDemo>nodeLists = new LinkedList<>();
        List<NodeDemo>nodeChildren = null;
        NodeDemo nodeStart = new NodeDemo();
        nodeStart.setX(xStart);
        nodeStart.setY(yStart);
        visited[xStart][yStart] = true;
        qe.add(nodeStart);
        while(!qe.isEmpty()){
            NodeDemo node = qe.remove();
            nodeChildren  = new ArrayList<>();
            for(int i= 0;i<4;i++){
                if(mazes[node.getX()][node.getY()]==3){
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX());
                    newNode.setY(node.getY());
                    visited[node.getX()][node.getY()]=true;
                    qe.add(node);
                    nodeChildren.add(newNode);
                    return nodeLists;
                }
                else if((node.getY()<(mazes.length-1))&& visited[node.getX()][node.getY()+1]==false && mazes[node.getX()][node.getY()+1]!=1){ // Phai
                    NodeDemo newNode  = new NodeDemo();
                    newNode.setX(node.getX());
                    newNode.setY(node.getY()+1);
                    visited[node.getX()][node.getY()+1]=true;
                    qe.add(newNode);
                    nodeChildren.add(newNode);
                }
                else if( node.getY()>0 && visited[node.getX()][node.getY()-1]==false && mazes[node.getX()][node.getY()-1]!=1){//Trai
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX());
                    newNode.setY(node.getY()-1);
                    visited[node.getX()][node.getY()-1]=true;
                    qe.add(newNode);
                    nodeChildren.add(newNode);
                }
                else if( node.getX()>0 && visited[node.getX()-1][node.getY()]==false && mazes[node.getX()-1][node.getY()]!=1){//Len
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX()-1);
                    newNode.setY(node.getY());
                    visited[node.getX()-1][node.getY()]=true;
                    qe.add(newNode);
                    nodeChildren.add(newNode);
                }
                else if( node.getX()<mazes.length-1 && visited[node.getX()+1][node.getY()]==false && mazes[node.getX()+1][node.getY()]!=1){//Xuong
                    NodeDemo newNode = new NodeDemo();
                    newNode.setX(node.getX()+1);
                    newNode.setY(node.getY());
                    visited[node.getX()+1][node.getY()]=true;
                    qe.add(newNode);
                    nodeChildren.add(newNode);
                }
            }
            NodeListDemo nodeList = new NodeListDemo();
            nodeList.setNodeParent(node);
            nodeList.setNodeChildren(nodeChildren);
            nodeLists.add(nodeList);
        }
        return null;
    }


    public Stack<NodeDemo> pathFindingBFS(LinkedList<NodeListDemo> qe){
        Stack<NodeDemo>pathFinding = new Stack<>();
        List<NodeDemo> pathBFS = new ArrayList<>();

        System.out.println("BFS:");
        for(int i = 0 ; i < qe.size();i++){
            for(int j = 0 ; j <qe.get(i).getNodeChildren().size();j++){
                int xEnd = qe.get(i).getNodeChildren().get(j).getX();
                int yEnd = qe.get(i).getNodeChildren().get(j).getY();
                if(mazes[xEnd][yEnd]==3){
                    pathBFS=(pintfPath(qe,i));
                    NodeDemo nodeEnd = new NodeDemo();
                    nodeEnd.setX(xEnd);
                    nodeEnd.setY(yEnd);
                    pathBFS.add(0,nodeEnd);
                }
            }
        }
        for(int i=pathBFS.size()-1;i>=0;i--){
            pathFinding.add(pathBFS.get(i));
        }
        return pathFinding;
    }

    public static List<NodeDemo> pintfPath(LinkedList<NodeListDemo>nodeLists , int index){
        List<NodeDemo>nodeList = new ArrayList<>();
        NodeDemo nodeParent = nodeLists.get(index).getNodeParent();
        nodeList.add(nodeParent);
        for(;index>=0;index--){
            for(int j=0;j<nodeLists.get(index).getNodeChildren().size();j++){
                if(nodeLists.get(index).getNodeChildren().get(j).equals(nodeParent)){
                    nodeParent = nodeLists.get(index).getNodeParent();
                    nodeList.add(nodeParent);
                }
            }
        }
        return nodeList;
    }


    /**
     * Algorithm A - START ( A*)
     *
     * */
    public Stack<NodeDemo> pathFindingAStart(){
        Stack<NodeDemo>pathFinding = new Stack<>();
        return pathFinding;
    }



    /**
     *  Check length of snake
     *  Check position of snake
     * */
    public boolean checkPositionNode(List<NodeDemo>positionNode,int x, int y){
        for(int i=0;i<positionNode.size();i++){
            if(y*30==positionNode.get(i).getX() && x*30==positionNode.get(i).getY()){
                return false;
            }
        }
        return true;
    }
}
