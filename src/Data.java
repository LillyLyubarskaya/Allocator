import java.util.ArrayList;
public class Data {
	public static ArrayList<Integer> sizeOfBlocks = new ArrayList<Integer>();
	public static final int PAGES_COUNT = 256;
	static{
		sizeOfBlocks.add(4);
		sizeOfBlocks.add(8);
		sizeOfBlocks.add(16);
		sizeOfBlocks.add(32);
		sizeOfBlocks.add(64);
		sizeOfBlocks.add(128);
		sizeOfBlocks.add(256);
		sizeOfBlocks.add(512);
		sizeOfBlocks.add(1024);
		sizeOfBlocks.add(2048);
	}
	public static int searchSize(int size){
		while (!sizeOfBlocks.contains(size)) {
			size++;
		}
		return size;
	}

}
