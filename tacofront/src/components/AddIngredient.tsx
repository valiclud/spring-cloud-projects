import { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import type { Ingredient } from '../types/ingredienttypes';
import { addIngredient } from '../api/ingredientapi';
import qIngredientDialogContent  from './IngredientDialogContent';
function AddIngredient() {
    const [open, setOpen] = useState(false);
    const [ingredient, setIngredient] = useState<Ingredient>({
        id: 0,
        code: '',
        name: '',
        type: ''
    });

    const handleClickOpen = () => {
        setOpen(true);
    }
    const handleClose = () => {
        setOpen(false);
    }
    const queryClient = useQueryClient();
    const { mutate } = useMutation({mutationFn: addIngredient,
        onSuccess: () => {
            queryClient.invalidateQueries(["ingredients"]);
        },
        onError: (err) => {
            console.error(err);
        },
    });
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setIngredient({...ingredient, [event.target.name]:
                event.target.value
        });
    }
    const handleSave = () => {
        mutate(ingredient);
        setIngredient({id: 0, code: '', name: '', type: ''});
        handleClose();
    }
    return (
        <>
            <button onClick={handleClickOpen}>New Ingredient</button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New ingredient</DialogTitle>
                <IngredientDialogContent ingredient={ingredient} handleChange={handleChange}/>
                <DialogActions>
                    <button onClick={handleClose}>Cancel</button>
                    <button onClick={handleSave}>Save</button>
                </DialogActions>
            </Dialog>
        </>
    );
}
export default AddIngredient;

