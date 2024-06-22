//package jsu.lmh.project1.config;
//
//import org.apache.commons.beanutils.ConvertUtils;
//import org.apache.shiro.authz.ModularRealmAuthorizer;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Set;
//
//public class UserModularRealmAuthorizer extends ModularRealmAuthorizer {
//    @Override
//    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
//        assertRealmsConfigured();
//        // 所有Realm
//        Collection<Realm> realms = getRealms();
//        HashMap<String, Realm> realmHashMap = new HashMap<>(realms.size());
//        for (Realm realm : realms) {
//            if (realm.getName().contains("Admin")) {
//                realmHashMap.put("AdminShiroRealm", realm);
//            } else if (realm.getName().contains("User")) {
//                realmHashMap.put("UserShiroRealm", realm);
//            }
//        }
//        Set<String> realmNames = principals.getRealmNames();
//        if (realmNames != null) {
//            String realmName = null;
//            Iterator it = realmNames.iterator();
//            while (it.hasNext()) {
//                realmName = ConvertUtils.convert(it.next());
//                if (realmName.contains("Admin")) {
//                    return ((AdminShiroRealm) realmHashMap.get("AdminShiroRealm")).hasRole(principals,roleIdentifier);
//                } else if (realmName.contains("User")) {
//                    return ((UserShiroRealm) realmHashMap.get("UserShiroRealm")).hasRole(principals,roleIdentifier);
//                }
//                break;
//            }
//        }
//        return false;
//    }
//}
