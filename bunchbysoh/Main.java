package bunchbysoh;

public class Main {
 
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    for( int capacity: presentCapacities)
      {
        double soh= 100.0 * capacity/120.0; //since rated_capacity is 120Ah for all use the same
        if(soh>80.0)    //if soh lies between 80.0 % and 100.0 % increase the count of healthy(means battery is healthy)
        {
          counts.healthy++;
        }
        else if(soh>62.0)   //if soh lies between 62.0 % and 80.0 % increase the count of exchange(means battery needs to be exchanged)
        {
          counts.exchange++;
        }
        else
        {
          counts.failed++;  //if soh lies below 62.0 % increase the count of failed(means battery has failed)
        }
      }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);

    int[] emptyArray = {};
    CountsBySoH emptyArrayCounts = countBatteriesByHealth(emptyArray);
    assert(emptyArrayCounts.healthy == 0);
    assert(emptyArrayCounts.exchange == 0);
    assert(emptyArrayCounts.failed == 0);

    int[] fullCapacity = {120, 120, 120};
    CountsBySoH fullCapacityCounts = countBatteriesByHealth(fullCapacity);
    assert(fullCapacityCounts.healthy == 3);
    assert(fullCapacityCounts.exchange == 0);
    assert(fullCapacityCounts.failed == 0);

    int[] minCapacity = {0, 10, 20};
    CountsBySoH minCapacityCounts = countBatteriesByHealth(minCapacity);
    assert(minCapacityCounts.healthy == 0);
    assert(minCapacityCounts.exchange == 0);
    assert(minCapacityCounts.failed == 3);

    int[] maxCapacity = {120, 119, 120};
    CountsBySoH maxCapacityCounts = countBatteriesByHealth(maxCapacity);
    assert(maxCapacityCounts.healthy == 2);
    assert(maxCapacityCounts.exchange == 1);
    assert(maxCapacityCounts.failed == 0);

    
   
    System.out.println("Count of healthy batteries : "+counts.healthy+"\n");
    System.out.println("Count of exchange batteries : "+counts.exchange+"\n");
    System.out.println("Count of failed batteries : "+counts.failed+"\n");
    System.out.println("Done counting :)\n");
  }
   public static void main(String[] args) {
    testBucketingByHealth();
  }
  
}
