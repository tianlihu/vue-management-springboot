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
