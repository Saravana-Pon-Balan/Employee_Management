import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class BranchDAO {
	
  private static Map<String, Branch> branches = new HashMap<>();
  private String branchId;
  
  public Branch isBranchFound(String id) {
    if (branches.containsKey(id)) {
      return branches.get(id);
    } else {
      this.branchId = id;
      return branches.get(id);
    }
  }
  
  public Branch insertBranch(Branch branchData) {
		String branchId = branchData.getId();
		branches.put(branchId, branchData);
		return branches.get(branchId);
  }
  
  public Branch fetchBranch(String id) {
    return branches.get(id);
  }
  
  public Branch deleteBranch(String id) {
    if (branches.containsKey(id)) {
      return branches.remove(id);
    } else {
      return null;
    }
  }
  
  public List<Branch> fetchAllBranch() {
    return new ArrayList<>(branches.values());
  }
  
}  