package com.tcs.codevita;
import java.util.*;
class Order {
    int id;
    int arrival;
    int pack;
    int vip;
    int startTime;
    int endTime;
    
    public Order(int id, int a, int p, int v) {
        this.id = id;
        arrival = a;
        pack = p;
        vip = v;
    }
}

public class MinimumChairs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Order> originalOrders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            originalOrders.add(new Order(i + 1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        
        System.out.println(findMaxChairs(originalOrders));
    }

    static int findMaxChairs(List<Order> originalOrders) {
        // Create a copy to avoid modifying original list
        List<Order> orders = new ArrayList<>();
        for (Order o : originalOrders) {
            orders.add(new Order(o.id, o.arrival, o.pack, o.vip));
        }
        
        orders.sort(Comparator.comparingInt(o -> o.arrival));
        
        PriorityQueue<Order> waitingQueue = new PriorityQueue<>((a, b) -> {
            if (a.vip != b.vip) {
                return b.vip - a.vip; // VIP (1) comes before normal (0)
            }
            return a.arrival - b.arrival; // Earlier arrival first
        });
        
        int currentTime = 0;
        int idx = 0;
        int maxChairs = 0;
        
        // Process all orders
        while (idx < orders.size() || !waitingQueue.isEmpty()) {
            while (idx < orders.size() && orders.get(idx).arrival <= currentTime) {
                waitingQueue.add(orders.get(idx));
                idx++;
            }
            
            // If no orders waiting and no orders arriving, break
            if (waitingQueue.isEmpty()) {
                if (idx < orders.size()) {
                    currentTime = orders.get(idx).arrival;
                    continue;
                } else {
                    break;
                }
            }
            
            // Process the highest priority order
            Order current = waitingQueue.poll();
            current.startTime = Math.max(currentTime, current.arrival);
            current.endTime = current.startTime + current.pack;
            
            // Update current time to when this order finishes
            currentTime = current.endTime;
            
            // Check waiting queue size at each arrival time between current order's start and end
            maxChairs = Math.max(maxChairs, waitingQueue.size());
            
            // Add any orders that arrived during processing
            while (idx < orders.size() && orders.get(idx).arrival < current.endTime) {
                waitingQueue.add(orders.get(idx));
                maxChairs = Math.max(maxChairs, waitingQueue.size());
                idx++;
            }
        }
        
        return maxChairs;
    }
}