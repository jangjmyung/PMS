package pmsc.perf;
import pmsc.*;
import pmsc.rcs.Catalog;

import java.util.*;

public interface PerformanceInterface {
	boolean storePerformance(CollectionEntity entity, HashMap<String, String>perfMap,  Set<Catalog>catalogSet);
	String getCatalog();
}
