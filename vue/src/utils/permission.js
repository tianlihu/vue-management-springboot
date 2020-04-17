import { getLoginUser } from './user';

export const setPermissions = permissions => {
    localStorage.setItem('permissions', JSON.stringify(permissions));
};

export const getPermissions = () => {
    let permissions = localStorage.getItem('permissions');
    if (permissions) {
        return JSON.parse(permissions);
    }
    return {};
};

export const removePermissions = () => {
    localStorage.removeItem('permissions');
};

export const hasPermission = (...urls) => {
    if (!urls || urls.length == 0) {
        return false;
    }
    let result = false;
    urls.forEach(url => {
        if (result) {
            return;
        }
        let user = {}; //getLoginUser();
        let permissions = getPermissions();
        if (!user.admin && url != '/dashboard') {
            let found = false;
            for (var i in permissions) {
                if (permissions[i].url === url) {
                    found = true;
                }
            }
            if (found) {
                result = true;
            }
        }
    });
    return result;
};

export default {
    install: function(Vue) {
        Vue.prototype.hasPermission = (...urls) => hasPermission(...urls);
        Vue.prototype.getPermissions = () => getPermissions();
        Vue.prototype.setPermissions = permissions => setPermission(permissions);
    }
};
