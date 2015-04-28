
public class Page {
	private Block [] data;
	public Page(int size){
		data=new Block[1];
		data[0]=new Block(size);
	}
	public Block getSystemBlock(){
		return data[0];
	}
	public void divide(int size,int blockSize){
		int blocksCount = size/blockSize;
		data = new Block[blocksCount];
		for (int i = 0; i < data.length; i++) {
			data[i] = new Block(blockSize);
		}
	}
	//we use 0 block to store system info
	public void setSystemInfo(PageTypes type,int size,int number){
		//set page type
		data[0].setByte(0, type.typeOf());
		//set page size
		data[0].setByte(1, size);
		//set page number
		data[0].setByte(2, number);
	}
	public int getType(){
		return getSystemBlock().getByte(0);
	}
	public int getSize(){
		return getSystemBlock().getByte(1);
	}
	public int getNumber(){
		return getSystemBlock().getByte(2);
	}
	//set link to first free block{
	public void setFreeBlockLink(int addr){
		data[0].setByte(3, addr);
	}
	public void take(int index, Block data) {
		if (index > this.data.length || index < 0) {
			throw new IllegalArgumentException();
		}
		this.data[index] = data;
	}
	

}
