package dz.jtsgen.processor.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TypeScriptModel {

    public static TypeScriptModel newModel() {
        return new TypeScriptModel();
    }

    private final List<TSType> tsTypes;

    private TypeScriptModel() {
        this.tsTypes = new ArrayList<>();
    }

    //copy constructor for renderer
    protected TypeScriptModel(TypeScriptModel ts) {
        this.tsTypes=ts.getTsTypes();
    }

    public void addTSTypes(final List<TSType> visitorResult) {
        if (visitorResult == null) return;

        Collection<TSType> removeThem = tsTypes.stream()
                .filter(
                        (x) -> tsTypes.stream().anyMatch(
                                y -> x.getName().equals(y.getName()) && x.getNamespace().equals(y.getNamespace())
                        )
                )
                .collect(Collectors.toList());

        tsTypes.removeAll(removeThem);
        tsTypes.addAll(visitorResult);
    }


    public List<TSType> getTsTypes() {
        return tsTypes;
    }


}