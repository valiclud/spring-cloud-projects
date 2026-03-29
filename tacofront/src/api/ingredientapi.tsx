import { type Ingredient } from '../types/ingredienttypes';
import axios from 'axios';

export const getIngredients = async (): Promise<Ingredient[]> => {
    const response = await axios.get(`${import.meta.env.VITE_API_URL}/api/ingredients`);
    return response.data.result;
}

export const deleteIngredient = async (dellData: string): Promise<Ingredient> => {
    const response = await axios.delete(dellData);
    return response.data
}

export const addIngredient = async (ingredient: Ingredient): Promise<Ingredient> => {
    const response = await axios.post(`${import.meta.env.VITE_API_URL}/api/ingredients`, ingredient, {
    headers: {
      'Content-Type': 'application/json',
    },  
  });
  return response.data;
}

export const updateIngredient = async (ingredient: Ingredient):
    Promise<Ingredient> => {
    const response = await axios.patch(`${import.meta.env.VITE_API_URL}/api/ingredients/${ingredient.id}`, ingredient, {
        headers: {
            'Content-Type': 'application/json'
        },
    });
    return response.data;
}