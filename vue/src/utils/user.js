export const setLoginUser = user => {
    localStorage.setItem('login_user', JSON.stringify(user));
};

export const getLoginUser = () => {
    let user = localStorage.getItem('login_user');
    if (user) {
        return JSON.parse(user);
    }
    return {};
};

export const removeLoginUser = () => {
    localStorage.removeItem('login_user');
};

export default {
    install: function(Vue) {
        Vue.prototype.getLoginUser = () => getLoginUser();
        Vue.prototype.setLoginUser = user => setLoginUser(user);
        Vue.prototype.removeLoginUser = () => removeLoginUser();
    }
};
