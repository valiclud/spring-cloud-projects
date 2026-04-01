import { updateIngredient } from '../api/ingredientapi';
import IngredientDialogContent from './IngredientDialogContent';
import { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import Tooltip from '@mui/material/Tooltip';
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
            <Tooltip title="Edit car">
            <IconButton aria-label="edit" size="small" onClick={handleClickOpen}>
            <EditIcon fontSize="small" />
                </IconButton>
            </Tooltip>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Edit ingredient</DialogTitle>
                <IngredientDialogContent ingredient={ingredient} handleChange={handleChange}/>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
export default EditIngredient;