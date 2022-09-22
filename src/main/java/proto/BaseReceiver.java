package proto;

import java.util.List;

import dao.Dao;

public class BaseReceiver<Entry, Key> implements Receiver<Entry, Key>
{
	protected Dao<Entry, Key> dao;
	
	public BaseReceiver(Dao<Entry, Key> dao) {
		this.dao = dao;
	}

	@Override
	public int createEntry(Entry entry) {
		return dao.save(entry);
	}

	@Override
	public int updateEntry(Entry entry) {
		return dao.update(entry);
	}

	@Override
	public int deleteEntry(Key key) {
		return dao.delete(key);
	}

	@Override
	public Entry readEntry(Key key) {
		return dao.get(key);
	}

	@Override
	public List<Entry> readEntries() {
		return dao.getAll();
	}
}
