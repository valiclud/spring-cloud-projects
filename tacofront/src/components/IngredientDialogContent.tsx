import DialogContent from '@mui/material/DialogContent';
import { type Ingredient } from '../types/ingredienttypes';

type DialogFormProps = {
    ingredient: Ingredient;
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) =>
        void;
}
function IngredientDialogContent({ ingredient, handleChange }: DialogFormProps) {
    return (
        <DialogContent>
            <input placeholder="Id" name="id"
                value={ingredient.id} onChange={handleChange} /><br />
            <input placeholder="Code" name="code"
                value={ingredient.code} onChange={handleChange} /><br />
            <input placeholder="Name" name="name"
                value={ingredient.name} onChange={handleChange} /><br />
            <input placeholder="Type" name="type"
                value={ingredient.type} onChange={handleChange} /><br />
        </DialogContent>
    );
}
export default IngredientDialogContent;