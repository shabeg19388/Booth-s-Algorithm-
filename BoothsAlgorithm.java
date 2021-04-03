import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class BoothsAlgorithm{
    public ArrayList<Integer> firstbinary;
    public ArrayList<Integer> secondbinary;
    public ArrayList<Integer> AC;
    public int cycles;
    public int QN_1;
    public boolean firstneg;
    public boolean secondneg;

    public BoothsAlgorithm(){
        firstbinary=new ArrayList<Integer>() ;
        secondbinary=new ArrayList<Integer>();
        AC = new ArrayList<Integer>();
        cycles=0;
        QN_1=0;
        firstneg=false;
        secondneg=false;
    }

    public ArrayList<Integer> binaryconversion(int num){
        ArrayList<Integer> temp=new ArrayList<Integer>();
        long binary=0;
        int pow=1;
        while(num>0){
            int lastbit=num%2;
            temp.add(lastbit);
            binary=binary+(lastbit*pow);
            pow=pow*10;
            num=num/2;
        }
        Collections.reverse(temp);
        return temp;
    }

    public ArrayList<Integer> get1sComplement(ArrayList<Integer> temp1){
        ArrayList<Integer> temp2=new ArrayList<Integer>();
        int i=0;
        while(i<temp1.size()){
            temp2.add(temp1.get(i));
            i++;
        }
        int j=0;
        while(j<temp2.size()){
            if(temp2.get(j)==0){
                temp2.set(j,1);
            }
            else{
                temp2.set(j,0);
            }
            j++;
        }
        int carry;
        carry=1;
        Collections.reverse(temp2);
        int[] sumcarryArr;
        for(int k=0; k<temp2.size(); k++){
            sumcarryArr=getcarrysum(carry, temp2.get(k));
            temp2.set(k, sumcarryArr[0]);
            carry= sumcarryArr[1];
            if(carry == 0)
                break;
        }
        Collections.reverse(temp2);
        return temp2;
    }
    public int[] getcarrysum(int x, int y){
        int[] arr=new int[2];
        if(x==0 && y==0){
            arr[0]=0;
            arr[1]=0;
        }
        if(x==0 && y==1){
            arr[0]=1;
            arr[1]=0;
        }
        if(x==1 && y==0){
            arr[0]=1;
            arr[1]=0;
        }
        if(x==1 && y==1){
            arr[0]=0;
            arr[1]=1;
        }
        return arr;
    }
    public ArrayList<Integer> binarynumsum(ArrayList<Integer> list1,ArrayList<Integer> list2) {
        ArrayList<Integer> tmpList1 = new ArrayList<Integer>();
        ArrayList<Integer> tmpList2 = new ArrayList<Integer>();
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for(int i=list1.size()-1;i>=0;i--){
            tmpList1.add(list1.get(i));
        }

        for(int i=list2.size()-1;i>=0;i--) {
            tmpList2.add(list2.get(i));
        }

        int tmpCarry1=0;
        int tmpCarry=0;
        int tmpAns=0;


        for(int i=0;i<tmpList1.size();i++) {
            int [] tmpArr;
            tmpArr = getcarrysum(tmpList1.get(i),tmpList2.get(i));
            tmpAns= tmpArr[0];
            tmpCarry1 = tmpArr[1];
            tmpArr = getcarrysum(tmpAns,tmpCarry);
            tmpAns=tmpArr[0];
            tmpCarry=tmpArr[1];
            answer.add(tmpAns);

            if(tmpCarry1 == 1 || tmpCarry == 1)
                tmpCarry=1;
            else
                tmpCarry=0;

        }
        Collections.reverse(answer);
        return answer;
    }

    public void ASR(){
        QN_1=secondbinary.get(secondbinary.size()-1);
        ArrayList<Integer> tempAC = new ArrayList<Integer>();
        ArrayList<Integer> tempSecondNum = new ArrayList<Integer>();
        int AClastbit = AC.get(AC.size() - 1);

        int i=0;
        while(i<AC.size()){
            tempAC.add(AC.get(i));
            i++;
        }
        int j=0;
        while(j<secondbinary.size()){
            tempSecondNum.add(secondbinary.get(j));
            j++;
        }

        int k=0;
        for (int l = 1; l < AC.size(); l++) {
            AC.set(l, tempAC.get(k));
            k++;
        }
        k=0;
        secondbinary.set(0,AClastbit);
        for (int m = 1; m < secondbinary.size(); m++) {
            secondbinary.set(m, tempSecondNum.get(k));
            k++;
        }
    }
    public void takeinput(){
        Scanner s =new Scanner(System.in);
        System.out.println("Enter multiplicand in decimal representation: ");
        int a=s.nextInt();
        System.out.println("Enter multiplier in decimal representation: ");
        int b=s.nextInt();

        boolean firstnumnegative;
        firstnumnegative=false;

        boolean secondnumnegative;
        secondnumnegative=false;

        if(a<0){
            firstnumnegative=true;
        }
        if(b<0){
            secondnumnegative=true;
        }

        if(firstnumnegative==true){
            firstbinary=binaryconversion(a*(-1));
        }
        else{
            firstbinary=binaryconversion(a);
        }

        if(secondnumnegative==true){
            secondbinary=binaryconversion(b*(-1));
        }
        else{
            secondbinary=binaryconversion(b);
        }

        int x;
        x=firstbinary.size();
        int y;
        y=secondbinary.size();
        if(x>y){
            int temp=x-y;
            int i=0;
            while(i<temp){
                secondbinary.add(0,0);
                i++;
            }
        }
        if(x<y){
            int temp=y-x;
            int i=0;
            while(i<temp){
                firstbinary.add(0,0);
                i++;
            }
        }
        firstbinary.add(0,0);
        secondbinary.add(0,0);

        boolean A;
        A=firstnumnegative;
        boolean B;
        B=secondnumnegative;

        if(A==true){
            ArrayList<Integer> templist= new ArrayList<Integer>();
            templist = get1sComplement(firstbinary);
            firstbinary = templist;
            firstneg=true;
        }
        if(B==true){
            ArrayList<Integer> templist=new ArrayList<Integer>();
            templist=get1sComplement(secondbinary);
            secondbinary=templist;
            secondneg=true;
        }
        int i=0;
        while(i<firstbinary.size()){
            AC.add(0);
            i++;
        }
        cycles=firstbinary.size();
    }

    public int getNum(ArrayList<Integer> tmpList){
        int num=0;
        Collections.reverse(tmpList);
        int i=0;
        for(i=0;i<tmpList.size()-1;i++){
            num=num + (int)((Math.pow(2,i))*tmpList.get(i));
        }
        num = num - (int)(Math.pow(2,i)*tmpList.get(tmpList.size()-1));
        return num;
    }
    public static void print(ArrayList<Integer> tmpList) {
        for(int i=0;i<tmpList.size();i++) {
            System.out.print(tmpList.get(i));
        }
        System.out.print("      ");
    }


    public void algorithm(){
        takeinput();
        ArrayList<Integer> complement=get1sComplement(firstbinary);
        ArrayList<Integer> ans=new ArrayList<Integer>();
        int i=0;
        BoothsAlgorithm tmp = new BoothsAlgorithm();
        System.out.println();
        System.out.print("Multiplicand in binary representation is: ");
        print(firstbinary);
        System.out.println();
        System.out.print("Multiplier in binary representation is: ");
        print(secondbinary);
        System.out.println();
        System.out.println();
        System.out.println("Steps performed are:");

        while(i<cycles){
            print(AC);
            print(secondbinary);
            System.out.println(QN_1);
            if(QN_1==0 && secondbinary.get(secondbinary.size()-1)==1){
                AC=binarynumsum(AC, complement);
                print(AC);
                print(secondbinary);
                System.out.println(QN_1);
                ASR();
                print(AC);
                print(secondbinary);
                System.out.println(QN_1);
            }
            else if(QN_1==1 && secondbinary.get(secondbinary.size()-1)==0){
                AC=binarynumsum(AC, firstbinary);
                print(AC);
                print(secondbinary);
                System.out.println(QN_1);
                ASR();
                print(AC);
                print(secondbinary);
                System.out.println(QN_1);
            }
            else{
                ASR();
                print(AC);
                print(secondbinary);
                System.out.println(QN_1);
            }

            System.out.println();
            i+=1;
        }
        for(int j=0;j<AC.size();j++) {
            ans.add(AC.get(j));
        }

        for(int j=0;j<secondbinary.size();j++) {
            ans.add(secondbinary.get(j));
        }
        System.out.println("Result in signed bit magnitude is: ");
        int k=0;
        while(k<ans.size()){
            System.out.print(ans.get(k));
            k++;
        }

        System.out.println();
        System.out.println();
        System.out.println("Result in decimal is: ");
        System.out.println(getNum(ans));
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        BoothsAlgorithm tmp=new BoothsAlgorithm();
        tmp.algorithm();
    }
}
