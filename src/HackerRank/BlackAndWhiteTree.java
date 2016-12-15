package HackerRank;

import com.sun.tools.javac.util.Pair;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by hmaduri on 6/11/16.
 */

//Nikita is making a graph as a birthday gift for her boyfriend, a fellow programmer! She drew an undirected connected graph with  nodes numbered from  to  in her notebook.
//
//    Each node is shaded in either white or black. We define  to be the number of white nodes, and  to be the number of black nodes. The graph is drawn in such a way that:
//
//    No  adjacent nodes have same coloring.
//    The value of , which we'll call , is minimal.
//    Nikita's mischievous little brother erased some of the edges and all of the coloring from her graph! As a result, the graph is now decomposed into one or more components.
//    Because you're her best friend, you've decided to help her reconstruct the graph by adding  edges such that the aforementioned graph properties hold true.
//
//    Given the decomposed graph, construct and shade a valid connected graph such that the difference  between its shaded nodes is minimal.
//
//    Input Format
//
//    The first line contains  space-separated integers,  (the number of nodes in the original graph) and (the number of edges in the decomposed graph), respectively.
//    The  subsequent lines each contain  space-separated integers,  and , describing a bidirectional edge between nodes  and  in the decomposed graph.
//
//    Constraints
//
//    It is guaranteed that every edge will be between  distinct nodes, and there will never be more than  edge between any  nodes.
//    Your answer must meet the following criteria:
//    The graph is connected and no  adjacent nodes have the same coloring.
//    The value of  is minimal.
//    Output Format
//
//    You must have  lines of output. The first line contains  space-separated integers:  (the minimum possible value of ) and  (the number of edges you've added to the graph),
//    respectively. Each of the  subsequent lines contains  space-separated integers,  and , describing a newly-added bidirectional edge in your final graph (i.e.: new edge ).
//
//    You may print any  of the possible reconstructions of Nikita's graph such that the value of  in the reconstructed shaded graph is minimal.
//
//    Sample Input 0
//
//    8 8
//    1 2
//    2 3
//    3 4
//    4 1
//    1 5
//    2 6
//    3 7
//    4 8
//    Sample output 0
//
//    0 0
//    Sample Input 1
//
//    8 6
//    1 2
//    3 4
//    3 5
//    3 6
//    3 7
//    3 8
//    Sample Output 1
//
//    4 1
//    1 5
//    Sample Input 2
//
//    5 4
//    1 2
//    2 3
//    3 4
//    4 1
//    Sample Output 2
//
//    1 2
//    2 5
//    4 5

public class BlackAndWhiteTree {

    public static Map<MetaComponent, Pair<MetaComponent,List<Pair<Integer,Integer>>>>  map = new HashMap<>();
    public static int numOfNodes;
    public static List<MetaComponent> metaComponents = new ArrayList<>();
    public static int numOfComponents = 1;

    public static class ComponentKey {
        public Integer node;
        public Boolean color;
        public Integer Componentnumber;

        public ComponentKey(Integer node, Boolean color, Integer ComponentNumber) {
            this.node = node;
            this.color = color;
            this.Componentnumber = ComponentNumber;
        }

        @Override
        public int hashCode(){
            // Start with a non-zero constant. Prime is preferred
            int result = 17;

            // Include a hash for each field.

            // Primatives

            result = 31 * result + (this.color ? 1 : 0);                   // 1 bit   » 32-bit

            result = 31 * result + this.node;                                // 8 bits  » 32-bit
            result = 31 * result + this.Componentnumber;                                // 16 bits » 32-bit
            return result;
        }

        @Override
        public boolean equals(Object o) {
            ComponentKey key = (ComponentKey) o;
            return this.Componentnumber == key.Componentnumber &&
                   this.color == key.color &&
                   this.node == key.node ? true : false;
        }
     }

    public static class Component {
        public Integer numOfNodes;
        public Map<Integer,List<Integer>> edges;
        public Set<Integer> nodes;
        public final int ID;

        public Component(int ID) {
            this.ID =ID;
        }

        public void add(int node) {
            numOfNodes++;
            nodes.add(node);
        }

        public void addEdge(int ii, int jj) {
            edges.get(ii).add(jj);
            edges.get(jj).add(ii);
         }
    }

    public static class MetaComponent {
        public Set<Integer> whites;
        public Set<Integer> blacks;
        public Set<Integer> compIds;

        @Override
        public int hashCode(){
            int result = 17;

            result = 31 * result + this.whites.hashCode();                               // 16 bits » 32-bit
            result = 31 * result + this.blacks.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object o) {
            MetaComponent key = (MetaComponent) o;
            return this.blacks.equals(((MetaComponent) o).blacks) &&
                   this.whites.equals(((MetaComponent) o).whites);
        }
    }

    public static void main(String[] args ) {
        Scanner sc = new Scanner(System.in);

        Pair<Integer, Integer> nodesAndEdges = Pair.of(sc.nextInt(),sc.nextInt());
        numOfNodes = nodesAndEdges.fst;
        Boolean[][] graphMatrix = new Boolean[nodesAndEdges.fst+1][nodesAndEdges.fst+1];

        for (int index=1;index<=nodesAndEdges.snd;index++) {
            Pair<Integer,Integer> edge = Pair.of(sc.nextInt(),sc.nextInt());
            graphMatrix[edge.fst][edge.snd] = true;
            graphMatrix[edge.snd][edge.fst] = true;
        }

        List<Component> components = getConnectedComponents(graphMatrix, nodesAndEdges.fst);

        for(Component comp : components) {
            color(comp);
        }

        Pair<Integer, List<Pair<Integer,Integer>>> output = combineTheComponents(metaComponents);

        System.out.println(output.fst+","+ output.snd.size());
        for(Pair<Integer,Integer> edges : output.snd) {
            System.out.println(edges.fst+","+edges.snd);
        }
    }

    public static List<Component> getConnectedComponents(Boolean[][] matrix, Integer numOfNodes) {
        Boolean[] visited = new Boolean[numOfNodes];
        List<Component> components = new ArrayList<>();

        for(Integer index=1;index<=numOfNodes;index++) {
            if(!visited[index]) {
                components.add(new Component(numOfComponents++));
                components.get(components.size()-1).add(index);
                visit(matrix, visited, index, components.get(components.size()-1), numOfNodes);
            }
        }
        return components;
    }

    public static void visit(Boolean[][] matrix, Boolean[] visited,
                             Integer index, Component comp, int numOfNodes) {
        if (visited[index]) return;
        visited[index] = true;
        for(int ii=1;ii<=numOfNodes;ii++) {
            if (matrix[index][ii]) {
                comp.add(ii);
                comp.addEdge(index,ii);
                visit(matrix, visited, ii, comp, numOfNodes);
            }
        }
    }

    public static void color(Component comp) {
        Set<Integer> whites = new HashSet<>();
        Set<Integer> blacks = new HashSet<>();

        Boolean[] visited = new Boolean[numOfNodes];
        Arrays.fill(visited, false);
        whites.add(comp.nodes.iterator().next());
        color(whites,blacks, visited, comp.nodes.iterator().next(), comp);
        initMap(comp,whites,blacks);
    }

    public static void color(Set<Integer> whites, Set<Integer> blacks,
                             Boolean[] visited, int index, Component comp) {
        if(visited[index]) return;
        boolean addBlackForChildren = whites.contains(index) ? true: false;
        for(Integer node: comp.edges.get(index)) {
            if(!visited[node]) {
                visited[node] = true;
                if(addBlackForChildren) blacks.add(node);
                else whites.add(node);
                color(whites,blacks,visited,node,comp);
            }
        }
    }

    public static void initMap(Component comp, Set<Integer> whites, Set<Integer> blacks) {
        MetaComponent metaComp = new MetaComponent();
        metaComp.blacks.addAll(blacks);
        metaComp.whites.addAll(whites);
        metaComp.compIds.add(comp.ID);
        metaComponents.add(metaComp);
        map.put(metaComp, Pair.of(metaComp,new ArrayList<Pair<Integer,Integer>>()));
//        for(Integer node : whites) {
//            map.put(new ComponentKey(node, true, comp.ID), Pair.of(whites.size(), blacks.size()));
//            map.put(new ComponentKey(node, false, comp.ID), Pair.of(blacks.size(), whites.size()));
//        }
//
//        for(Integer node: blacks) {
//            map.put(new ComponentKey(node, false, comp.ID), Pair.of(whites.size(), blacks.size()));
//            map.put(new ComponentKey(node, true, comp.ID), Pair.of(blacks.size(), whites.size()));
//        }
    }

    public static Pair<Integer,List<Pair<Integer,Integer>>>
                combineTheComponents(List<MetaComponent> components) {
//        List<MetaComponent> metacomponents = prepareMetaComponents(components);


        for(MetaComponent comp : components)
            for(MetaComponent comp1 : components) {
                if(!comp.equals(comp1))
                    combine(comp,comp1,components);
            }
        return null;
    }

    public static boolean updateMap(MetaComponent meta, Pair<Integer, Integer> joinedPair) {
        Pair<MetaComponent, List<Pair<Integer,Integer>>> storedMeta = map.get(meta);
        if (storedMeta ==  null ||
            Math.abs(meta.blacks.size() - meta.whites.size()) < Math.abs(storedMeta.fst.blacks.size() - storedMeta.fst.whites.size())) {
            map.put(meta, Pair.of(meta, new ArrayList<>()));
                return true;
        }
        return false;
    }

    public static void combine(MetaComponent comp1, MetaComponent comp2, List<MetaComponent> components) {
        List<MetaComponent> comps = new ArrayList<>(components);
        comps.remove(comp1);
        comps.remove(comp2);

        MetaComponent metaComponent = new MetaComponent();
        metaComponent.blacks.addAll(comp1.blacks);
        metaComponent.blacks.addAll(comp2.blacks);
        metaComponent.whites.addAll(comp2.whites);
        metaComponent.whites.addAll(comp1.whites);
        Pair<Integer, Integer> joinedPair = Pair.of(comp1.blacks.iterator().next(), comp2.whites.iterator().next());
        if (updateMap(metaComponent, joinedPair)) {
            comps.add(metaComponent);
            combineTheComponents(comps);
            comps.remove(metaComponent);
        }
        MetaComponent metaComponent1 = new MetaComponent();
        metaComponent1.blacks.addAll(comp1.whites);
        metaComponent1.blacks.addAll(comp2.whites);
        metaComponent1.whites.addAll(comp2.blacks);
        metaComponent1.whites.addAll(comp1.blacks);
        Pair<Integer, Integer> joinedPair1 = Pair.of(comp1.blacks.iterator().next(), comp2.whites.iterator().next());
        if( updateMap(metaComponent1, joinedPair1)) {
            comps.add(metaComponent1);
            combineTheComponents(comps);
            comps.remove(metaComponent1);
        }
    }
}
