import { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import type { Ingredient } from '../types/ingredienttypes';
import { addIngredient } from '../api/ingredientapi';
import IngredientDialogContent  from './IngredientDialogContent';
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
            <Button onClick={handleClickOpen}>New Ingredient</Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>New ingredient</DialogTitle>
                <IngredientDialogContent ingredient={ingredient} handleChange={handleChange}/>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
export default AddIngredient;

