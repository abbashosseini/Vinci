package mklib.hosseini.com.vinci.list;

public final class ImmutableList<E> {

	public final E top;
	public final ImmutableList<E> last;

	public ImmutableList() {
		this.top = null;
		this.last = null;
	}

	private ImmutableList(E top, ImmutableList<E> last) {
		this.top = top;
		this.last = last;
	}

	public ImmutableList<E> prepend(E element) {
		return new ImmutableList<E>(element, this);
	}
	
	public <E2> ImmutableList<E2> transform(Function<? super E, ? extends E2> fn) {
		return last == null
			? new ImmutableList<E2>()
			: new ImmutableList<E2>(fn.apply(top), last.transform(fn));
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
		if (!(obj instanceof ImmutableList))
			return false;
		ImmutableList other = (ImmutableList) obj;
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