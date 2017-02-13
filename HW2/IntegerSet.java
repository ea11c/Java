// Class:   IntegerSet
// Author:  Eric Adams
// For:     COP3252, Java Programming

public class IntegerSet {
    private boolean[] Set;
    private static int SIZE = 101;
    public IntegerSet(){
        Set = new boolean[SIZE];
        for(int i = 0; i < Set.length; i++){
            Set[i] = false;
        }
    }
    public IntegerSet union(IntegerSet iSet){
        IntegerSet result = new IntegerSet();

        for(int i = 0; i < this.Set.length; i++){  //loops through both sets to check if the number is in either set
            if(this.Set[i] || iSet.Set[i]){
                result.Set[i] = true;
            }
        }
        return result;
    }
    public IntegerSet intersection(IntegerSet iSet){
        IntegerSet result = new IntegerSet();

        for(int i = 0; i < this.Set.length; i++){  //loops through both sets to check if the number is in both sets
            if(this.Set[i] && iSet.Set[i]){
                result.Set[i] = true;
            }
        }
        return result;
    }
    public IntegerSet insertElement(int data){
        this.Set[data] = true;
        return this;
    }
    public IntegerSet deleteElement(int data){
        this.Set[data] = false;
        return this;
    }
    public boolean isEqualTo(IntegerSet iSet){
        boolean result = true;

        for(int i = 0; i < this.Set.length; i++){ //loops through both sets to see if they are the same at each value
            if(this.Set[i] != iSet.Set[i]){
                result = false;
            }
        }

        return result;
    }
    public String toString(){
        String result = new String();
        boolean flag = true;

        for(int i = 0; i < this.Set.length; i++){
            if (this.Set[i]){
                result += i + " ";
                flag = false;
            }
        }

        if(flag){
            result = "---";
        }

        return result;
    }

}
