package ru.package01;

import java.util.*;

public class AnyObject {
    public byte byteA;
    public short shortA;
    public int intA;
    public long longA;
    public String StringA;
    public float floatA;
    public double doubleA = Math.PI;
    public char charA = 'A';
    public boolean BooleanA;
    public Byte byteB;
    public Short ShortB;
    public Integer integerB;
    public Long longB;
    public Float floatB;
    public Double doubleB = Math.PI * 2;
    public Character characterB = 'B';
    public Boolean BooleanB;

    public byte[] arrayByteA = new byte[3];
    public short[] arrayShortA = new short[3];
    public int[] arrayIntA = new int[3];
    public long[] arrayLongA = new long[3];
    public String[] arrayStringA = new String[3];
    public float[] arrayFloatA = new float[3];
    public double[] arrayDoubleA = new double[3];
    public char[] arrayCharA = new char[3];
    List ArraylistA = new ArrayList();
    List LinkedlistA = new LinkedList();
    Map hashMapA = new HashMap<Integer, Character>();
    Map treeMapA = new TreeMap<Integer, Double>();
    Set HashSetA = new HashSet();
    Set TreeSetA = new TreeSet();
    Deque ArrayDequeA = new ArrayDeque<>();
    Queue PriorityQueueA = new PriorityQueue();

    AnyObject() {

    }

    public AnyObject(int intA, String StringA, long longA, byte byteA) {
        this.intA = intA;
        this.StringA = StringA;
        this.longA = longA;
        this.byteA = byteA;
        ArraylistA.add(55);        ArraylistA.add("33");        ArraylistA.add(23);
        LinkedlistA.add('t');        LinkedlistA.add("rty");        LinkedlistA.add(66.9);

        hashMapA.put(10, 'f');        hashMapA.put(11, 'f');        hashMapA.put(12, 'f');
        hashMapA.put(1, 42.3);        hashMapA.put(2, 34.7);        hashMapA.put(3, 65.2);
        HashSetA.add(23);        HashSetA.add(5);        HashSetA.add('w');        HashSetA.add("sdf");

        TreeSetA.add("87.2");        TreeSetA.add("15.3");        TreeSetA.add("23.2");        TreeSetA.add("5");
//        TreeSetA.add('d'); TreeSetA.add('d');TreeSetA.add('a'); TreeSetA.add('s');
//        TreeSetA.add(25.3); TreeSetA.add(25.2);TreeSetA.add(25d); TreeSetA.add(25.0);
        ArrayDequeA.add(55);        ArrayDequeA.add(55.3);        ArrayDequeA.add('h');        ArrayDequeA.add("dd");
        PriorityQueueA.add("12.3");        PriorityQueueA.add("12");
    }
}
