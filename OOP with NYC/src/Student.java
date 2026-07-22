public class Student {

    String name;
    int age;

}


class Demo{

    public static void main(String[] args) {
        Student s1 = new Student();
                s1.name = "Dubey";
        s1.age = 22;

        Student s2 = new Student();
        s2.name = "Priyanshu";
        s2.age = 22;
        System.out.println(s1.name + "-" + s1.age);
    }


}