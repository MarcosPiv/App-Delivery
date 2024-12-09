import React, { useState } from 'react';
import { useCategories } from '../../hooks/useCategories';
import CategorySelector from './CategorySelector';
import NewCategoryForm from './NewCategoryForm';
import { createItemMenu, updateItemMenu } from '../../services/itemsMenuService.ts'

interface MenuItemFormProps {
    onSubmit: (data: any) => void;
    initialData?: any;
    onCancel: () => void;
}

const MenuItemForm = ({ onSubmit, initialData, onCancel }: MenuItemFormProps) => {
    const [useExistingCategory, setUseExistingCategory] = useState(true);
    const [newCategory, setNewCategory] = useState<any>(null);
    const { categories } = useCategories();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const formData = new FormData(e.target as HTMLFormElement);
        const data = Object.fromEntries(formData.entries());

        const menuItemData = {
            tipoItem: data.tipoItem,
            nombre: data.nombre,
            descripcion: data.descripcion,
            precio: parseFloat(data.precio as string),
            peso: parseFloat(data.peso as string),
            graduacionAlcoholica: parseFloat(data.graduacionAlcoholica as string),
            tamanio: parseFloat(data.tamanio as string),
            aptoVegano: data.aptoVegano === 'on',
            aptoCeliaco: data.aptoCeliaco === 'on',
            categoria: useExistingCategory
                ? { id: parseInt(data.categoriaId as string, 10) }
                : newCategory,
            calorias: parseFloat(data.calorias as string),
            pesoSinEnvase: parseFloat(data.pesoSinEnvase as string)
        };

        try {
            if (initialData) {
                // Llamada al servicio para actualizar un ItemMenu
                await updateItemMenu(initialData.id, menuItemData);
                alert('Item actualizado exitosamente.');
            } else {
                // Llamada al servicio para crear un nuevo ItemMenu
                await createItemMenu(menuItemData);
                alert('Item creado exitosamente.');
            }

            if (onSubmit) {
                onSubmit(menuItemData);
            }
        } catch (error) {
            console.error('Error al guardar el item:', error);
            alert('Hubo un error al guardar el item. Por favor, intenta nuevamente.');
        }
    };


    return (
        <form onSubmit={handleSubmit} className="space-y-4 max-h-[80vh] overflow-y-auto px-4">
            <div className="grid grid-cols-2 gap-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Nombre</label>
                    <input
                        type="text"
                        name="nombre"
                        defaultValue={initialData?.nombre}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Descripción</label>
                    <input
                        type="text"
                        name="descripcion"
                        defaultValue={initialData?.descripcion}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
            </div>
            <div className="space-y-4">
                <div className="flex items-center space-x-4">
                    <label className="text-sm font-medium text-gray-700 dark:text-gray-300">Categoría:</label>
                    <div className="flex space-x-4">
                        <label className="inline-flex items-center">
                            <input
                                type="radio"
                                checked={useExistingCategory}
                                onChange={() => setUseExistingCategory(true)}
                                className="form-radio"
                            />
                            <span className="ml-2 text-gray-700 dark:text-gray-300">Usar existente</span>
                        </label>
                        <label className="inline-flex items-center">
                            <input
                                type="radio"
                                checked={!useExistingCategory}
                                onChange={() => setUseExistingCategory(false)}
                                className="form-radio"
                            />
                            <span className="ml-2 text-gray-700 dark:text-gray-300">Crear nueva</span>
                        </label>
                    </div>
                </div>

                {useExistingCategory ? (
                    <CategorySelector
                        // @ts-ignore
                        categories={categories}
                        defaultValue={initialData?.categoria?.id}
                    />
                ) : (
                    <NewCategoryForm
                        onCategoryCreate={setNewCategory}
                    />
                )}
            </div>

            <div className="grid grid-cols-2 gap-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Precio</label>
                    <input
                        type="number"
                        name="precio"
                        step="0.01"
                        defaultValue={initialData?.precio}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Peso</label>
                    <input
                        type="number"
                        name="peso"
                        step="0.01"
                        defaultValue={initialData?.peso}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Tipo</label>
                    <select
                        name="tipoItem"
                        defaultValue={initialData?.tipo || "Comida"}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    >
                        <option value="Comida">Comida</option>
                        <option value="Bebida">Bebida</option>
                    </select>
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Graduación Alcohólica</label>
                    <input
                        type="number"
                        name="graduacionAlcoholica"
                        step="0.1"
                        defaultValue={initialData?.graduacionAlcoholica}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Tamaño</label>
                    <input
                        type="number"
                        name="tamanio"
                        defaultValue={initialData?.tamanio}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
                <div>
                    <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Calorías</label>
                    <input
                        type="number"
                        name="calorias"
                        defaultValue={initialData?.calorias}
                        className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                        required
                    />
                </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
                <div className="flex items-center">
                    <input
                        type="checkbox"
                        name="aptoVegano"
                        defaultChecked={initialData?.aptoVegano}
                        className="h-4 w-4 text-blue-600 rounded border-gray-300"
                    />
                    <label className="ml-2 text-sm text-gray-700 dark:text-gray-300">Apto Vegano</label>
                </div>
                <div className="flex items-center">
                    <input
                        type="checkbox"
                        name="aptoCeliaco"
                        defaultChecked={initialData?.aptoCeliaco}
                        className="h-4 w-4 text-blue-600 rounded border-gray-300"
                    />
                    <label className="ml-2 text-sm text-gray-700 dark:text-gray-300">Apto Celíaco</label>
                </div>
            </div>

            <div>
                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">Peso Sin Envase</label>
                <input
                    type="number"
                    name="pesoSinEnvase"
                    step="0.01"
                    defaultValue={initialData?.pesoSinEnvase}
                    className="mt-1 block w-full rounded-md border dark:border-gray-600 dark:bg-gray-700 dark:text-white shadow-sm p-2"
                    required
                />
            </div>

            <div className="flex justify-end space-x-2 mt-6">
                <button
                    type="button"
                    onClick={onCancel}
                    className="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-100 dark:bg-gray-700 dark:text-gray-300 rounded-md hover:bg-gray-200 dark:hover:bg-gray-600"
                >
                    Cancelar
                </button>
                <button
                    type="submit"
                    className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700"
                >
                    {initialData ? 'Guardar Cambios' : 'Agregar Item'}
                </button>
            </div>
        </form>
    );
};

export default MenuItemForm;