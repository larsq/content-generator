/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Randomizer with weighted probability.
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 * @param <T>
 */
public class Randomizer<T> {
    
    public static class WeightedItem<T> implements Comparable<WeightedItem<T>> {
        private final int weight;
        private final T instance;

        public WeightedItem(int weight, T instance) {
            this.weight = weight;
            this.instance = instance;
        }
        
        public static <T> WeightedItem<T> of(int weight, T instance) {
            return new WeightedItem<>(weight, instance);
        }

        @Override
        public String toString() {
            return String.format("%s (%s)", instance, weight);
        }

        @Override
        public int compareTo(WeightedItem<T> o) {
            return Integer.compare(weight, o.weight);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this || obj == null) {
                return obj == this;
            }
            
            if(!Objects.equals(getClass(), obj.getClass())) {
                return false;
            }
            
            WeightedItem<T> other = getClass().cast(obj);
            
            return weight == other.weight && Objects.equals(instance, other.instance);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, instance);
        }
        
        
    }
    
    public static <T> Randomizer<T> of(Map<T, Integer> map) {
        T[] items = (T[]) map.keySet().toArray();
        int[] weights = new int[items.length];
        
        for(int i=0;i<items.length;i++) {
            weights[i] = map.get(items[i]);
        }
        
        return new Randomizer<>(items, weights);
    }
    
    public static <T> Randomizer<T> uniformProbability(T... items) {
        return new Randomizer<>(items, null);
    }
    
    private final T[] items;
    private final int[] accumulatedWeights;
    private final int n;
    private final Random random;

    public Randomizer(T[] items, int[] weights) {
        Preconditions.checkNotNull(items);
        Preconditions.checkArgument(items.length > 1, "Items must be more than 1"); 
        
        this.items = items;
        this.accumulatedWeights = new int[items.length];
        this.n = accumulate(weights, accumulatedWeights) + 1;
        this.random = new Random();
    }
    
    public Stream<T> stream() {
        return Stream.generate(()->next());
    }

    public T next() {
        int number = random.nextInt(n);
        return items[findIndex(number, accumulatedWeights)];
    }
    
    private static int weightOf(int index, int[] weights) {
        if(weights == null) {
            return 1;
        }
        
        if(weights.length <= index) {
            return 1;
        }
        
        return weights[index];
    }
    
    private static int accumulate(int[] array, int[] accumulated) {     
        int sum = 0;
        for (int i = 0; i < accumulated.length; i++) {
            sum += weightOf(i, array);
            accumulated[i] = sum;
        }

        return sum;
    }

    private static int findIndex(int n, int[] sortedArray) {
        if(sortedArray[sortedArray.length-1]<n) {
            throw new IllegalArgumentException(String.format("n cannot exceed %s: %s", sortedArray[sortedArray.length-1], n));
        }
        
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i]>=n) {
                return i;
            }
        }
        
        throw new AssertionError("unexpected part of code");
    }
}
