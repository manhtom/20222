public class ArrayManipulation {
    int[] a;
    public ArrayManipulation(int[] inputarray){ // this is not the place to initialize attributes
        a = inputarray;
    }

    public int[] BubbleSort(){
        int temp=0;
        while (true){
            int swapped=0;
            for (int i=0;i<a.length-1;i++){
                if (this.a[i]>this.a[i+1]){
                    temp=this.a[i];
                    this.a[i]=this.a[i+1];
                    this.a[i+1]=temp;
                    swapped=1;
                }
            }
            if (swapped==0){break;}
        }
        return a;
    }
    public int Sum(){
        int result=0;
        for (int i=0;i<a.length;i++){
            result=result+this.a[i];
        }
        return result;
    }

    public float Average(){
        return this.Sum()/this.a.length;
    }
}
