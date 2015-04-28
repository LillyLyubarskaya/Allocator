import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Block {
	private int[]  data;
	//list of block sizes
	// 0 element is link to next free block
	public int getLink(){
		return data[0];
	}
	public void setLink(int link){
		data[0]=link;
	}
	//generate random data
	public void generateData(int [] data){
		Random random=new Random();
		for (int i = 1; i < data.length; i++) {
			data[i]=random.nextInt();
		}
	}
	public void generateData(int [] data,int arg0){
		Random random=new Random();
		for (int i = arg0; i < data.length; i++) {
			data[i]=random.nextInt();
		}
	}
	public Block(int size){
		data=new int[size];
		generateData(data);
	}
	public void setData(int [] data){
		this.data=data;
	}
	public int [] getData(){
		return data;
	}
	public Block (int [] data){
		setData(data);
	}
    public int getSize(){
    	return data.length;
    }
    public void change(int newSize){
    	newSize=Data.searchSize(newSize);
    	int oldSize=getSize();
    	data=Arrays.copyOf(data, newSize);
    	if(newSize>oldSize){
    		generateData(data,oldSize);
    	}
    }
    public void setByte(int index,int info){
    	data[index]=info;
    }
    public int getByte(int index){
    	return data[index];
    }
}
