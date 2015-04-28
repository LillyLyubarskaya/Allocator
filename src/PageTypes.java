public enum PageTypes {
		SYSTEM,
		FREE,
		BUSY,
		DIVIDED;
		public int typeOf() {
			return (int) ordinal()+1;
		}
	}
