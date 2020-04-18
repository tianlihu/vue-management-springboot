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
    let user = getLoginUser();
    if (user.admin) {
        return true;
    }
    let result = false;
    let permissions = getPermissions();
    urls.forEach(url => {
        if (result) {
            return;
        }
        if (url != '/dashboard') {
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
        Vue.prototype.setPermissions = permissions => setPermissions(permissions);
    }
};
