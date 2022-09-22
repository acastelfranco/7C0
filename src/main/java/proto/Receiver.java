package proto;

import java.util.List;

public interface Receiver<Entry, Key> {
	
	public int createEntry(Entry entry);

	public int updateEntry(Entry entry);
	
	public int deleteEntry(Key key);
	
	public Entry readEntry(Key key);
	
	public List<Entry> readEntries();
}
