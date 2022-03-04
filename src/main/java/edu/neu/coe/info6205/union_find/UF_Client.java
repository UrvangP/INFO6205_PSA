package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_Client {

    public static int count(int n){
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(n,true);

        Random rand = new Random();
        int num_connections = 0;

        while(uf_hwqupc.components()!=1){
            int p = rand.nextInt(n);
            int q = rand.nextInt(n);

            if( p!=q && !uf_hwqupc.connected(p, q) ) {
                uf_hwqupc.union(p, q);
            }

            num_connections++;
        }

        return num_connections;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        System.out.println("Number of sites: " + n);
        System.out.println("Number of connections: " + count(n));
//        System.out.println(n + "," + count(n));

    }
}
