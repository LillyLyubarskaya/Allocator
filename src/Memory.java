import java.util.ArrayList;

import utils.Utils;
import allocator.Constants;
import allocator.Page;
import allocator.Constants.PageTypes;



public class Memory {
	Page [] pages=new Page[Data.PAGES_COUNT];
	private int pageSize;
	public Memory(int size) {
		// 0 page contains system info
		pageSize = size/Data.PAGES_COUNT;
		for (int i = 0; i < Data.PAGES_COUNT; i++) {
			pages[i]=new Page(pageSize);
		}
		// create 10 block (4, 8, 16, ...) pages
		for (int i = 0; i < Data.sizeOfBlocks.size(); i++) {
			pages[i+1].divide(pageSize,Data.sizeOfBlocks.get(i));
		}
		// initialize system page
		pages[0].getSystemBlock().setByte(0, 11);
		for (int i = 0, j = 1; i < Data.sizeOfBlocks.size(); i++, j += 5) {
			int currentBlockSize = Data.sizeOfBlocks.get(i);
			// block size
			pages[0].getSystemBlock().setByte(j, currentBlockSize);
			// first free page divided into blocks with these size
			//if all are busy, -1
			pages[0].getSystemBlock().setByte(j + 1, i + 1);
			// blocks count in these page
			pages[0].getSystemBlock().setByte(j + 2, pageSize/currentBlockSize);
			// first free block
			pages[0].getSystemBlock().setByte(j + 3, 0);	
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public Page getPage(int index) {
		return pages[index];
	}
	protected int[] findPages(int pagesCount) {
		if (pagesCount > Data.PAGES_COUNT) {
			return null;
		}
		int firstFreePageNo = pages[0].getSystemBlock().getByte(0);
		int[] pageNo = new int[pagesCount];
		int amount=0;
		while(amount<pagesCount){
			//TODO
		}
		return pageNo;
	}
	protected int occupyBlock(int pageNo, int size) {
		int offset = Constants.getSizeOffset(size);
		// free page ¹ | block size | free page with blocks ¹ | blocks count |
		// free block ¹ | next block size | ...		
		int blockSizePointer = Constants.START + offset*Constants.ALLIGNMENT;
		int freePageNoPointer = 0;
		int freePageNo = getSystemBlock().getByte(freePageNoPointer);
		int blocksCountPointer = blockSizePointer + 2;
		int blocksCount = getSystemBlock().getByte(blocksCountPointer);
		blocksCount--;
		int freeBlockNoPointer = blocksCountPointer + 1;
		int freeBlockNo = getSystemBlock().getByte(freeBlockNoPointer);
		if (blocksCount == 0) {
			// create new page
			freePageNo += 1;
			getSystemBlock().setByte(freePageNoPointer, freePageNo);
			freePageNo--;
			int freeBlockingPagePointer = blockSizePointer + 1;
			getSystemBlock().setByte(freeBlockingPagePointer, freePageNo);
			int correctSize = Constants.correctSize(size);
			blocksCount = pageSize/correctSize;
			getSystemBlock().setByte(blocksCountPointer, blocksCount);
			getSystemBlock().setByte(freeBlockNoPointer, 0);
			pages.get(freePageNo).setType(PageTypes.DIVIDED);
			pages.get(freePageNo).divide(correctSize);
				return pages.get(pageNo).occupy(freeBlockNo);
		} else {
			getSystemBlock().setByte(blocksCountPointer, blocksCount);
			int freeBlockNoIncremented = freeBlockNo + 1;
			getSystemBlock().setByte(freeBlockNoPointer,
				freeBlockNoIncremented);
				return pages.get(pageNo).occupy(freeBlockNo);
		}
	}

}
