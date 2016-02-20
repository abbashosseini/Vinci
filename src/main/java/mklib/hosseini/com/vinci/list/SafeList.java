package mklib.hosseini.com.vinci.list;

public final class SafeList<E> {

	public final E top;
	public final SafeList<E> last;

	public SafeList() {
		this.top = null;
		this.last = null;
	}

	private SafeList(E top, SafeList<E> last) {
		this.top = top;
		this.last = last;
	}

	public SafeList<E> prepend(E element) {
		return new SafeList<E>(element, this);
	}
	
	public <E2> SafeList<E2> transform(Function<? super E, ? extends E2> fn) {
		return last == null
			? new SafeList<E2>()
			: new SafeList<E2>(fn.apply(top), last.transform(fn));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((top == null) ? 0 : top.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SafeList))
			return false;
		SafeList other = (SafeList) obj;
		if (top == null) {
			if (other.top != null)
				return false;
		} else if (!top.equals(other.top))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}
	
	
	
	
}