package com.company;

import java.util.*;

/**
 * Created by hmaduri on 6/25/16.
 */
public class RouteBetweenTwoNodes {

    public static void main(String[] args) {
        DirectedAcyclicGraph<Integer,Integer> dag = new DirectedAcyclicGraph<>();
        for (int i=0;i<5;i++)
            dag.addVertex(i);

        dag.addEdge(1,3,11);
        dag.addEdge(0,1,12);
        dag.addEdge(3,4,1);
        dag.addEdge(0,2,14);

        boolean pathexists = isThereAPathBetweenNodes(0,4,dag);
        System.out.println(pathexists);
    }

    public static boolean isThereAPathBetweenNodes(int v1, int v2, DirectedAcyclicGraph<Integer,Integer> dag) {
        Map<Integer,Boolean> marked = new HashMap<>();
        boolean exists = false;
        marked.put(v1, true);
        if (dag.getAdjacentEdgesList(v1) != null)
        for(DirectedAcyclicGraph.Edge<Integer,Integer> edge : dag.getAdjacentEdgesList(v1)) {
            if (marked.get(edge.other(v1)) == null || !marked.get(edge.other(v1)))
                exists = dfs(v2,marked,dag,edge.other(v1));
            if (exists)
                return exists;
        }
        return false;
    }

    public static boolean dfs(Integer searchVertex, Map<Integer,Boolean> marked, DirectedAcyclicGraph<Integer,Integer> dag, Integer v) {
        if (v.equals(searchVertex))
            return true;
        marked.put(v,true);
        if (dag.getAdjacentEdgesList(v) != null)
            for(DirectedAcyclicGraph.Edge<Integer,Integer> edge : dag.getAdjacentEdgesList(v)) {
                if (marked.get(edge.other(v)) == null || !marked.get(edge.other(v)))
                    return dfs(searchVertex,marked,dag,edge.other(v));
            }
        return false;
    }

    public static class DirectedAcyclicGraph<N,W> {
        private Set<N> nodes = new HashSet<>();
        private Set<Edge<N,W>> edges = new HashSet<>();
        private Map<N,Set<Edge<N,W>>> adjlist = new HashMap<>();

        public static class Edge<N,W> {
            private final N start;
            private final N end;
            private final W weight;

            public Edge(N start, N end, W weight) {
                this.start = start;
                this.end = end;
                this.weight = weight;
            }

            public N either() {
                return this.start;
            }

            public N other(N vertex) {
                return start.equals(vertex) ? this.end : this.start;
            }
        }

        public DirectedAcyclicGraph() {


        }

        public void addVertex(N vertex) {
            nodes.add(vertex);
        }

        public void addEdge(N v1, N v2, W weight) {
            Edge<N,W> edge = new Edge<N,W>(v1,v2,weight);
            if (adjlist.get(v1) == null)
                adjlist.put(v1, new HashSet<>());
            adjlist.get(v1).add(edge);
            edges.add(edge);
        }

        public Set<Edge<N,W>> getAdjacentEdgesList(N v1) {
            return adjlist.get(v1);
        }
    }
}
