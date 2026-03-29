import { updateIngredient } from '../api/ingredientapi';
import IngredientDialogContent from './IngredientDialogContent';
import { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import type { Ingredient } from '../types/ingredienttypes';
import { useMutation, useQueryClient } from '@tanstack/react-query';

type FormProps = {
    ingredientdata: Ingredient;
}
function EditIngredient({ ingredientdata }: FormProps) {
    const queryClient = useQueryClient();
    // Use useMutation hook
    const { mutate } = useMutation({mutationFn: updateIngredient,
        onSuccess: () => {
            queryClient.invalidateQueries(["ingredients"]);
        },
        onError: (err) => {
            console.error(err);
        }
    });

    const [open, setOpen] = useState(false);
    const [ingredient, setIngredient] = useState<Ingredient>({
        id: 0,
        code: '',
        name: '',
        type: ''
    });
    const handleClickOpen = () => {
        setIngredient({
            id: ingredientdata.id,
            code: ingredientdata.code,
            name: ingredientdata.name,
            type: ingredientdata.type
        });
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    const handleSave = () => {
        mutate(ingredient);
        setIngredient({ id: 0, code: '', name: '', type: '' });
        setOpen(false);
    }
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setIngredient({ ...ingredient, [event.target.name]: event.target.value });
    }

    return (
        <>
            <button onClick={handleClickOpen}>
                Edit
            </button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Edit ingredient</DialogTitle>
                <IngredientDialogContent ingredient={ingredient} handleChange={handleChange}/>
                <DialogActions>
                    <button onClick={handleClose}>Cancel</button>
                    <button onClick={handleSave}>Save</button>
                </DialogActions>
            </Dialog>
        </>
    );
}
export default EditIngredient;